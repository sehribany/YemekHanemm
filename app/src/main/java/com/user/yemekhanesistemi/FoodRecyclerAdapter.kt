package com.user.yemekhanesistemi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.user.yemekhanesistemi.databinding.RecyclerRow2Binding
import com.user.yemekhanesistemi.databinding.RecyclerRow3Binding
import kotlinx.android.synthetic.main.recycler_row2.view.*

class FoodRecyclerAdapter(val foodList:ArrayList<Foods>): RecyclerView.Adapter<FoodRecyclerAdapter.FoodHolder>() {

    class FoodHolder(var view: RecyclerRow2Binding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view= DataBindingUtil.inflate<RecyclerRow2Binding>(inflater,R.layout.recycler_row2,parent,false)
        return FoodHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.view.besin1=foodList[position]



    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}
