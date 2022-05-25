package com.aspk.aspk.ui.home.menu.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspk.aspk.data.local.PromoData
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.model.PromoModel
import com.aspk.aspk.databinding.FragmentPromoBinding
import com.aspk.aspk.ui.home.menu.food.FoodAdapter
import com.aspk.aspk.util.SessionManagement
import splitties.toast.toast


class PromoFragment: Fragment() {

    lateinit var binding: FragmentPromoBinding
    lateinit var adapterFood: PromoAdapter
    lateinit var sessionManagement: SessionManagement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPromoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManagement = SessionManagement(requireActivity())
        setupRecycler()
        setRecyclerView(PromoData.listPromo)
    }

    private fun setupRecycler() {
        adapterFood = PromoAdapter (onItemClick = {
            sessionManagement.applyPromo(it.discount,it.name)
            toast("Promo ${it.name} applied")
        })
        with(binding.rvPromo){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterFood
        }
    }

    private fun setRecyclerView(food: ArrayList<PromoModel>){
        adapterFood.setTask(food)
        adapterFood.notifyDataSetChanged()
    }
}