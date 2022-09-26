package com.user.yemekhanesistemi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.user.yemekhanesistemi.databinding.RecyclerRow3Binding

class BesinAdapter(var c:Context ,val foodListesi:ArrayList<Foods>):RecyclerView.Adapter<BesinAdapter.FoodsHolder>() {

    class FoodsHolder(var view:RecyclerRow3Binding):RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<RecyclerRow3Binding>(inflater,R.layout.recycler_row3,parent,false)
        return FoodsHolder(view)
    }

    override fun onBindViewHolder(holder: FoodsHolder, position: Int) {
        val newList=foodListesi[position]
        holder.view.besin=foodListesi[position]

        holder.view.root.setOnClickListener {

            val kisiSayisi=newList.kisiSayisi
            val tarih=newList.tarih
            val anayemek=newList.anayemek
            val icecekk=newList.icecek
            val tatli=newList.tatli
            val meyve=newList.meyve
            val fiyat=newList.fiyat
            val myInent=Intent(c,BesinDetayi::class.java)

            myInent.putExtra("txt_pkisiSayisi",kisiSayisi)
            myInent.putExtra("txt_ptarih",tarih)
            myInent.putExtra("txt_panayemek",anayemek)
            myInent.putExtra("txt_picecek",icecekk)
            myInent.putExtra("txt_ptatli",tatli)
            myInent.putExtra("txt_pmeyve",meyve)
            myInent.putExtra("txt_pfiyat",fiyat)
            c.startActivity(myInent)
        }
    }

    override fun getItemCount(): Int {
        return foodListesi.size
    }



}