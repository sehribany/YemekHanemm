package com.user.yemekhanesistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main_register_ekrani.*

class MainRegisterEkrani : AppCompatActivity() {
    private lateinit var store:FirebaseFirestore
    private lateinit var isim: TextView
    private lateinit var mail: TextView
    private lateinit var parola: TextView
    private lateinit var admin: CheckBox
    private lateinit var personel: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register_ekrani)

        store= FirebaseFirestore.getInstance()

        isim=findViewById(R.id.kayitisimSoyad)
        mail=findViewById(R.id.kayitKullaniciAdi)
        parola=findViewById(R.id.kayitParola)
        admin=findViewById(R.id.checkBoxAdmin)
        personel=findViewById(R.id.checkBoxPersonel)

    }

    fun kayitol(view:View){
        val isimsoyad=isim.text.toString()
        val email=mail.text.toString()
        val password=parola.text.toString()
        val adminsec=admin.isChecked
        val personelsec=personel.isChecked

        val userHashMap=hashMapOf<String,Any>()
        userHashMap.put("isimsoyisim",isimsoyad)
        userHashMap.put("kullaniciemail",email)
        userHashMap.put("kullaniciparola",password)
        userHashMap.put("adminmi",adminsec)
        userHashMap.put("personelmi",personelsec)

        if(email.isNullOrEmpty()==false && password.isNullOrEmpty()==false && isimsoyad.isNullOrEmpty()==false){
            if(password.length>=6){
                if(adminsec==true && personelsec==false || adminsec==false && personelsec==true){

                    store.collection("Users").add(userHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(applicationContext,"Yeni Kişi Oluşturuldu.Giriş Yapınız",Toast.LENGTH_LONG).show()
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Sadece bir tane seçenek seçiniz.",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Şifreniz en az 6 karakterli olmalıdır.",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext,"Bilgilerinizi tam giriniz..",Toast.LENGTH_LONG).show()
        }

    }

    fun geriDon(view: View){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}