package com.user.yemekhanesistemi

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var store: FirebaseFirestore
    var userListesi=ArrayList<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        store= FirebaseFirestore.getInstance()
    }

    fun login(view:View){
        var email = editTextPersonName.text.toString()
        var password = editTextPersonPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            store.collection("Users").addSnapshotListener { value, error ->
                if(error != null){
                    Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
                }else{
                    if(value!=null){
                        if(!value.isEmpty){
                            val documents=value.documents
                            for(document in documents){
                                var e=document.get("kullaniciemail")
                                var p=document.get("kullaniciparola")
                                var a=document.get("adminmi")
                                var c=document.get("personelmi")
                                if(email==e && password==p && a==true && c==false){
                                    val intent=Intent(this,AdminGirisSayfasi::class.java)
                                    startActivity(intent)
                                    finish()
                                }else if(email==e && password==p && a==false && c==true){
                                    val intent=Intent(this,PersonelGirisSayfasi::class.java)
                                    startActivity(intent)
                                    finish()
                                }else if(email==e && password != p){
                                    Toast.makeText(applicationContext,"Şifrenizi kontrol ediniz.",Toast.LENGTH_SHORT).show()
                                }else if(email !=e && password == p){
                                    Toast.makeText(applicationContext,"E-mail bilgisini kontrol ediniz.",Toast.LENGTH_SHORT).show()
                                }
                            }}}}}
        }else{

            Toast.makeText(applicationContext,"Email ve şifre giriniz.",Toast.LENGTH_SHORT).show()
        }
    }

    fun register(view:View){
        val intent = Intent(this, MainRegisterEkrani::class.java)
        startActivity(intent)
        finish()

    }
}