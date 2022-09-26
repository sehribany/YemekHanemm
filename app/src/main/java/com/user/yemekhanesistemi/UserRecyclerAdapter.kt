package com.user.yemekhanesistemi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.user.yemekhanesistemi.databinding.RecylerRowBinding
import kotlinx.android.synthetic.main.recyler_row.view.*

class UserRecyclerAdapter(var c: Context, var userList:ArrayList<Users>): RecyclerView.Adapter<UserRecyclerAdapter.UserHolder>(){

    class UserHolder(var view: RecylerRowBinding):RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<RecylerRowBinding>(inflater,R.layout.recyler_row,parent,false)
        return UserHolder(view)


    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.view.users=userList[position]

        val newList=userList[position]

        holder.view.root.setOnClickListener {

            val isim=newList.kullaniciIsim
            val email=newList.kullaniciEmail
            val parola=newList.kullaniciPassword
            val admin=newList.adminmi.toString()
            val personel=newList.personelmi.toString()
            val myInent=Intent(c,UserDetayi::class.java)

            myInent.putExtra("isim",isim)
            myInent.putExtra("email",email)
            myInent.putExtra("parola",parola)
            myInent.putExtra("admin",admin)
            myInent.putExtra("personel",personel)
            myInent.putExtra("gisim",isim)
            myInent.putExtra("gmail",email)
            myInent.putExtra("gparola",parola)
            myInent.putExtra("gadmin",admin)
            myInent.putExtra("gpersonel",personel)
            c.startActivity(myInent)
        }

    }

    override fun getItemCount(): Int {
        return userList.size

    }
}