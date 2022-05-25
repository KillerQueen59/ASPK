package com.aspk.aspk.ui.home.menu.promo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.model.PromoModel
import com.aspk.aspk.databinding.ItemFoodBinding
import com.aspk.aspk.databinding.ItemPromoBinding
import com.aspk.aspk.ui.home.menu.food.FoodAdapter
import com.bumptech.glide.Glide

class PromoAdapter (private val onItemClick: (food: PromoModel) -> Unit) : RecyclerView.Adapter<PromoAdapter.FoodViewHolder>() {
    private var listFood = ArrayList<PromoModel>()

    fun setTask(food: ArrayList<PromoModel>?) {
        if (food == null) return
        this.listFood.clear()
        this.listFood.addAll(food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    class FoodViewHolder(private val binding: ItemPromoBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: PromoModel) {
            with(binding) {
                Glide.with(context)
                    .load(food.image)
                    .into(imagePromo)
            }
        }


    }
}