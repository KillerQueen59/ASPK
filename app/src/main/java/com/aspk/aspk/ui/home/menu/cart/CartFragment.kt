package com.aspk.aspk.ui.home.menu.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspk.aspk.R
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.room.FoodDatabase
import com.aspk.aspk.databinding.FragmentCartBinding
import com.aspk.aspk.ui.auth.ContainerAuthFragmentDirections
import com.aspk.aspk.ui.home.menu.food.FoodAdapter
import com.aspk.aspk.util.SessionManagement
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import splitties.toast.toast

class CartFragment: Fragment() {

    lateinit var binding: FragmentCartBinding
    private lateinit var database: FoodDatabase
    lateinit var  items : List<FoodEntity>
    lateinit var adapterFood: CartAdapter
    private var total = 0
    lateinit var sessionManagement: SessionManagement
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }
    private var saveAsync = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManagement = SessionManagement(requireActivity())
        database = FoodDatabase.getInstance(requireContext())
        setupRecycler()
        getCurrentFood()
        binding.btnPesan.setOnClickListener {
            pesan()
            if (saveAsync){
                toast("Berhasil Memesan")
                goToHome()
            }
        }
        binding.btnBack.setOnClickListener {
            homeAuthController?.navigateUp()
        }
    }

    private fun getCurrentFood(){
        GlobalScope.launch {
            total = 0
            items = database.foodDao().getAllFood().filter { it.cart }
            items.map {
               total+= (it.qty * it.price)
            }
            val array = arrayListOf<FoodEntity>()
            array.addAll(items)
            setRecyclerView(array)
            binding.tvTotal.text = total.toString()
            if (sessionManagement.promo == 0f){
                binding.cartPromo.visibility = View.GONE
                binding.frameDiscount.visibility = View.GONE
                binding.tvTotalPrice.text = total.toString()
            } else {
                binding.cartPromo.visibility = View.VISIBLE
                binding.frameDiscount.visibility = View.VISIBLE
                binding.textView7.text = sessionManagement.promoName
                binding.tvAfterDiscount.text = (total * sessionManagement.promo).toString()
                binding.tvTotalPrice.text =  (total - (total * sessionManagement.promo)).toString()
            }
        }
    }

    private fun pesan(){
        GlobalScope.launch {
            database.foodDao().getAllFood().map {
                val data = it
                data.cart = false
                data.qty = 0
                data.avalableIn = ""
                data.sweetness = ""
                database.foodDao().addCart(data)
            }
            sessionManagement.clearPromo()
            saveAsync = true
        }
    }

    private fun setupRecycler() {
        adapterFood = CartAdapter(onItemClick = {
            if (it.drink){
                goToDrinkDetail(it)
            } else {
                goToFoodDetail(it)
            }
        })
        with(binding.recyclerView){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterFood
        }
    }

    private fun setRecyclerView(food: ArrayList<FoodEntity>){
        adapterFood.setTask(food)
        adapterFood.notifyDataSetChanged()
    }

    private fun goToHome(){
        val direction = CartFragmentDirections.actionCartFragmentToHomeFragment()
        homeAuthController?.navigate(direction)
    }


    private fun goToFoodDetail(data: FoodEntity){
        val direction = CartFragmentDirections.actionCartFragmentToDetailFoodFragment2(data,"cart")
        homeAuthController?.navigate(direction)
    }


    private fun goToDrinkDetail(data: FoodEntity){
        val direction = CartFragmentDirections.actionCartFragmentToDetailDrinkFragment(data,"cart")
        homeAuthController?.navigate(direction)
    }

}