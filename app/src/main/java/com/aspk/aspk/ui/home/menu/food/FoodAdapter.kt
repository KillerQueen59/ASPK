package com.aspk.aspk.ui.home.menu.food

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

class FoodAdapter(private val onItemClick: (food: FoodEntity) -> Unit) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private var listFood = ArrayList<FoodEntity>()

    fun setTask(food: ArrayList<FoodEntity>?) {
        if (food == null) return
        this.listFood.clear()
        this.listFood.addAll(food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    class FoodViewHolder(private val binding: ItemFoodBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodEntity) {
            with(binding) {
                titleFood.text = food.name ?: ""
                ingredientFood.text = food.desc ?: ""
                Glide.with(context)
                    .load(food.image)
                    .into(imageFood)
                priceFood.text = food.price.toString() ?: ""

            }
        }


    }
}