package com.user.yemekhanesistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_personel_ekle.*

class PersonelEkle : AppCompatActivity() {
    private lateinit var store:FirebaseFirestore
    private lateinit var isim: TextView
    private lateinit var mail: TextView
    private lateinit var parola: TextView
    private lateinit var admin: CheckBox
    private lateinit var personel: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personel_ekle)
        store= FirebaseFirestore.getInstance()

        isim=findViewById(R.id.adminpersonelisim)
        mail=findViewById(R.id.adminkayitKullaniciAdi)
        parola=findViewById(R.id.adminkayitParola)
        admin=findViewById(R.id.admincheckBoxAdmin)
        personel=findViewById(R.id.admincheckBoxPersonel)
    }

    fun kayitet(view: View){
        val isimsoyad=isim.text.toString()
        val email=mail.text.toString()
        val password=parola.text.toString()
        val adminsec=admin.isChecked
        val personelsec=personel.isChecked


        if(email.isNotEmpty() && password.isNotEmpty() && isimsoyad.isNotEmpty()){
            if(password.length>=6){
                if(adminsec==true && personelsec==false || adminsec==false && personelsec==true){

                    val userHashMap=hashMapOf<String,Any>()
                    userHashMap.put("isimsoyisim",isimsoyad)
                    userHashMap.put("kullaniciemail",email)
                    userHashMap.put("kullaniciparola",password)
                    userHashMap.put("adminmi",adminsec)
                    userHashMap.put("personelmi",personelsec)

                    store.collection("Users").add(userHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(applicationContext,"Yeni Kişi Oluşturuldu.",Toast.LENGTH_LONG).show()
                            val intent= Intent(this,PersonelAyarlari::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(applicationContext,it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Sadece bir tane seçenek seçiniz.", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Şifreniz en az 6 karakterli olmalıdır.", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext,"Kişi Bilgilerini tam giriniz.", Toast.LENGTH_LONG).show()
        }
    }
    fun geriDonus(view:View){
        val intent2=Intent(this,PersonelAyarlari::class.java)
        startActivity(intent2)
        finish()
    }
}