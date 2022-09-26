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
import kotlinx.android.synthetic.main.activity_personel_ayarlari.*

class PersonelAyarlari : AppCompatActivity() {

    private lateinit var store:FirebaseFirestore
    private lateinit var recyclerAdapter: UserRecyclerAdapter
    var userListesi=ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personel_ayarlari)
        store= FirebaseFirestore.getInstance()
        var layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerAdapter= UserRecyclerAdapter(this,userListesi)
        recyclerView.adapter=recyclerAdapter

        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F44336")))
        verileriAl()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.personelsecenekmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.yeniekle){
            val intent= Intent(this,PersonelEkle::class.java)
            startActivity(intent)
            finish()

        }else if(item.itemId==R.id.geridon){

            val intent=Intent(this,AdminGirisSayfasi::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun verileriAl(){
        store.collection("Users").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents
                        userListesi.clear()
                        for(document in documents){
                            val kullaniciIsim=document.get("isimsoyisim") as String
                            val kullaniciEmail=document.get("kullaniciemail") as String
                            val kullaniciPassword=document.get("kullaniciparola") as String
                            val adminmi=document.get("adminmi") as Boolean
                            val personelmi=document.get("personelmi") as Boolean

                            val user=Users(kullaniciIsim,kullaniciEmail,kullaniciPassword, adminmi, personelmi)

                            userListesi.add(user)

                        }
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}