package com.user.yemekhanesistemi

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_personel_giris_sayfasi.*


class PersonelGirisSayfasi : AppCompatActivity() {
    private lateinit var store: FirebaseFirestore
    var foodListesi=ArrayList<Foods>()
    private lateinit var recyclerViewAdapter:BesinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personel_giris_sayfasi)

        store= FirebaseFirestore.getInstance()

        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F44336")))

        verileriAl()

        var layoutManager= LinearLayoutManager(this)
        recyclerView3.layoutManager=layoutManager
        recyclerViewAdapter=BesinAdapter(this,foodListesi)
        recyclerView3.adapter=recyclerViewAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.cikisyap,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.cikis){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(applicationContext,"Çıkış yapıldı",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun verileriAl() {
        store.collection("Foods").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents =value.documents
                        foodListesi.clear()
                        for(document in documents){
                            val kisiSayisi=document.get("kisiSayisi") as String
                            val tarih=document.get("tarih") as String
                            val anayemek=document.get("anayemek") as String
                            val icecek=document.get("icecek") as String
                            val tatli=document.get("tatli") as String
                            val meyve=document.get("meyve") as String
                            val fiyat=document.get("fiyat") as String

                            val food=Foods(kisiSayisi,tarih, anayemek, icecek, tatli, meyve, fiyat)
                            foodListesi.add(food)
                        }
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

    }
}