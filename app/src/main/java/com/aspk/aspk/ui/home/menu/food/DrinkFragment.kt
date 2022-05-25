package com.aspk.aspk.ui.home.menu.food

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
import com.aspk.aspk.databinding.FragmentFoodBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import splitties.toast.toast

class DrinkFragment: Fragment() {

    lateinit var binding: FragmentFoodBinding
    lateinit var  data : List<FoodEntity>
    private lateinit var database: FoodDatabase
    private val compositeDisposable = CompositeDisposable()
    lateinit var adapterDrink: FoodAdapter
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }
    lateinit var  items : List<FoodEntity>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FoodDatabase.getInstance(requireContext())
        setupRecycler()
        getAllFood()
        getCurrentFood()
        with(binding){
            cvCart.setOnClickListener {
                goToCart()
            }
        }
    }

    private fun getCurrentFood(){
        GlobalScope.launch {
            items = database.foodDao().getAllFood().filter { it.cart }
            with(binding) {
                if (items.size != 0) {
                    totalItem.text = "${items.size} items"
                    var item = ""
                    for (w in items.indices) {
                        item += if (w == items.size - 1) {
                            items[w].name
                        } else "${items[w].name}, "
                    }
                    listItem.text = item
                    var price = 0
                    items.map {
                        price += (it.price * it.qty)
                    }
                    totalPrice.text = "Rp. $price"
                }
            }
            if (items.isEmpty()) binding.cvCart.visibility = View.GONE
            else binding.cvCart.visibility = View.VISIBLE
        }
    }

    private fun getAllFood(){
        GlobalScope.launch {
            data = database.foodDao().getAllFood().filter { it.drink }
            val array = arrayListOf<FoodEntity>()
            array.addAll(data)
            setRecyclerView(array)
        }
    }

    private fun setupRecycler() {
        adapterDrink = FoodAdapter (onItemClick = {
            goToDetail(it)
        })
        with(binding.rvFood){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterDrink
        }
    }

    private fun setRecyclerView(food: ArrayList<FoodEntity>){
        adapterDrink.setTask(food)
        adapterDrink.notifyDataSetChanged()
    }

    private fun goToDetail(data: FoodEntity){
        val direction = DrinkFragmentDirections.actionDrinkFragmentToDetailDrinkFragment(data,"food")
        homeAuthController?.navigate(direction)
    }

    private fun goToCart(){
        val direction = DrinkFragmentDirections.actionDrinkFragmentToCartFragment()
        homeAuthController?.navigate(direction)
    }


}