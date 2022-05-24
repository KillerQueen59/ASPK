package com.aspk.aspk.ui.home.menu.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.aspk.aspk.R
import com.aspk.aspk.data.local.room.FoodDatabase
import com.aspk.aspk.databinding.FragmentDetailDrinkBinding
import com.aspk.aspk.databinding.FragmentDetailFoodBinding
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import splitties.toast.toast

class DetailDrinkFragment: Fragment() {

    lateinit var binding: FragmentDetailDrinkBinding
    private val args: DetailFoodFragmentArgs by navArgs()
    private lateinit var database: FoodDatabase
    private val compositeDisposable = CompositeDisposable()
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }
    private lateinit var viewModel: DetailViewModel
    lateinit var count: LiveData<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailDrinkBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProviders.of(this)[DetailViewModel::class.java]

        database = FoodDatabase.getInstance(requireContext())
        initUi()
        initAction()
        viewModel.setInitNumber(args.data.qty)
        count = viewModel.getInitialcount()
        count.observe(requireActivity(), Observer {
            if (it == 0){
                binding.subsItem.visibility = View.INVISIBLE
            } else {
                binding.subsItem.visibility = View.VISIBLE
            }
            binding.item.text = it.toString()
        })
    }

    private fun initUi(){
        with(binding){
            titleFood.text = args.data.name
            priceFood.text = args.data.price.toString()
            ingredientFood.text = args.data.desc
            Glide.with(requireContext())
                .load(args.data.image)
                .into(imageFood)
            if (args.data.note != "") etNotes.setText(args.data.note)
            if (args.data.qty != 0) item.text = args.data.qty.toString()
            when(args.data.avalableIn){
                "Small" -> rbSmall.isChecked = true
                "Normal" -> rbNormal.isChecked = true
                "Large" -> rbLarge.isChecked = true
                "" -> {
                    rbSmall.isChecked = false
                    rbNormal.isChecked = false
                    rbLarge.isChecked = false
                }
            }
            when(args.data.sweetness){
                "Hot" -> rbHot.isChecked = true
                "Normal" -> rbIce.isChecked = true
                "" -> {
                    rbHot.isChecked = false
                    rbIce.isChecked = false
                }
            }
        }
    }

    private fun initAction(){
        with(binding){
            addToCart.setOnClickListener {
                if (requireActivity().findViewById<RadioButton>(binding.rgAvailable.checkedRadioButtonId) != null
                    && requireActivity().findViewById<RadioButton>(binding.rgSweetness.checkedRadioButtonId) != null
                    && count.value != 0) addToCart()
                else toast("tidak boleh kosong")
            }

            subsItem.setOnClickListener {
                viewModel.sub()
            }

            addItem.setOnClickListener {
                viewModel.add()
            }

        }
    }

    private fun addToCart(){
        val data = args.data
        data.qty = binding.item.text.toString().toInt()
        data.avalableIn = requireActivity().findViewById<RadioButton>(binding.rgAvailable.checkedRadioButtonId).text.toString()
        data.sweetness = requireActivity().findViewById<RadioButton>(binding.rgSweetness.checkedRadioButtonId).text.toString()
        data.note = binding.etNotes.text.toString()
        data.cart = true
        compositeDisposable.add(Completable.fromRunnable { database.foodDao().addCart(data)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                with(binding){
                    homeAuthController?.navigateUp()
                }
            },{
            }))
    }
}