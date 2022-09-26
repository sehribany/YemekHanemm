package com.user.yemekhanesistemi

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_besin_detayi.*
import kotlinx.android.synthetic.main.activity_user_detayi.*

class UserDetayi : AppCompatActivity() {
    private lateinit var store: FirebaseFirestore
    private lateinit var eisim:TextView
    private lateinit var eemail:TextView
    private lateinit var eparola:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detayi)
        store = FirebaseFirestore.getInstance()


        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F44336")))

        val userIntent = intent
        val userIsim = userIntent.getStringExtra("isim")
        val userEmail = userIntent.getStringExtra("email")
        val userParola = userIntent.getStringExtra("parola")
        val guserIsim=userIntent.getStringExtra("gisim")
        val guserEmail=userIntent.getStringExtra("gmail")
        val guserParola=userIntent.getStringExtra("gparola")
        val guserAdmin=userIntent.getStringExtra("gadmin")
        val guserPersonel=userIntent.getStringExtra("gpersonel")


        isim.setText(userIsim)
        email.setText(userEmail)
        parola.setText(userParola)
        gisim.setText(guserIsim)
        gmail.setText(guserEmail)
        gparola.setText(guserParola)
        gadmin.setText(guserAdmin)
        gpersonel.setText(guserPersonel)

    }
    fun sil(view:View){

        var isim= gisim.text.toString()
        var ma=gmail.text.toString()
        var pa=gparola.text.toString()

        store.collection("Users").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents
                        for(document in documents){
                            var key=document.id
                            var q=document.get("isimsoyisim")
                            var e=document.get("kullaniciemail")
                            val p=document.get("kullaniciparola")
                            val a=document.get("adminmi")
                            val per=document.get("personelmi")
                            if(isim==q && ma==e && pa==p){
                                System.out.println(key)
                                store.collection("Users").document(key).delete()
                                Toast.makeText(this,"Kullanıcı Silindi",Toast.LENGTH_SHORT).show()
                                val intent=Intent(this,PersonelAyarlari::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                val intent=Intent(this,PersonelAyarlari::class.java)
                                startActivity(intent)
                                finish()

                            }
                        }}}}
        }
    }

    fun güncelle(view:View){
        eisim=findViewById(R.id.gisim)
        eemail=findViewById(R.id.gmail)
        eparola=findViewById(R.id.gparola)

        var kullanicisim= eisim.text.toString()
        var kullanicimail=eemail.text.toString()
        var kullanicipassword=eparola.text.toString()


        store.collection("Users").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents

                        for(document in documents){
                            var k=document.id
                            var t=document.get("isimsoyisim")
                            var y=document.get("kullaniciemail")
                            var i=document.get("kullaniciparola")
                            var tat=document.get("adminmi")
                            var m=document.get("personelmi")
                            if(kullanicisim==t && kullanicimail==y && kullanicipassword==i){
                                store.collection("Users").document(k).
                                update("isimsoyisim",isim.text.toString(),
                                    "kullaniciemail",email.text.toString(),
                                "kullaniciparola",parola.text.toString())
                                Toast.makeText(this,"Güncellendi",Toast.LENGTH_SHORT).show()
                                val intent=Intent(this,PersonelAyarlari::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                val intent=Intent(this,PersonelAyarlari::class.java)
                                startActivity(intent)
                                finish()
                            }

                        }
                    }
        }}}}


}
