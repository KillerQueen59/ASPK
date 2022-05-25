package com.aspk.aspk.ui.home.menu.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.databinding.ItemCartBinding
import com.aspk.aspk.databinding.ItemFoodBinding
import com.aspk.aspk.ui.home.menu.food.FoodAdapter
import com.bumptech.glide.Glide

class CartAdapter (private val onItemClick: (food: FoodEntity) -> Unit) : RecyclerView.Adapter<CartAdapter.FoodViewHolder>() {
    private var listFood = ArrayList<FoodEntity>()

    fun setTask(food: ArrayList<FoodEntity>?) {
        if (food == null) return
        this.listFood.clear()
        this.listFood.addAll(food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = listFood[position]
        holder.bind(food)
        holder.itemView.setOnClickListener {
            onItemClick(food)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }


    class FoodViewHolder(private val binding: ItemCartBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodEntity) {
            with(binding) {
                name.text = food.name ?: ""
                description.text = food.desc ?: ""
                price.text = (food.price * food.qty).toString() ?: ""
                qty.text = food.qty.toString() + "x"

            }
        }


    }
}