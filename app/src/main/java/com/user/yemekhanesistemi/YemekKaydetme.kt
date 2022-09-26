package com.user.yemekhanesistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_yemek_kaydetme.*

class YemekKaydetme : AppCompatActivity() {
    private lateinit var store:FirebaseFirestore

    private lateinit var kisisayisi: TextView
    private lateinit var tarih: TextView
    private lateinit var yemek: TextView
    private lateinit var icecek: TextView
    private lateinit var tatli: TextView
    private lateinit var meyve: TextView
    private lateinit var fiyat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_kaydetme)

        store= FirebaseFirestore.getInstance()
        calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val i4= i2+1
            txt_tarih.setText(""+i3+"/"+i4+"/"+i)
        }

        kisisayisi=findViewById(R.id.txt_kisiSayisi)
        tarih=findViewById(R.id.txt_tarih)
        yemek=findViewById(R.id.txt_anayemek)
        icecek=findViewById(R.id.txt_icecek)
        tatli=findViewById(R.id.txt_tatli)
        meyve=findViewById(R.id.txt_meyve)
        fiyat=findViewById(R.id.txt_fiyat)

    }

    fun yemekKaydet(view: View){
        val tarih=tarih.text.toString()
        val anayemek=yemek.text.toString()
        val kisiSayisi=kisisayisi.text.toString()
        val icecek=txt_icecek.text.toString()
        val tatli=tatli.text.toString()
        val meyve=meyve.text.toString()
        val fiyat=fiyat.text.toString()

        if(tarih.isNullOrEmpty()==false && anayemek.isNullOrEmpty()==false && icecek.isNullOrEmpty()==false&& tatli.isNullOrEmpty()==false && meyve.isNullOrEmpty()==false && fiyat.isNullOrEmpty()==false){

            val foodHashMap=hashMapOf<String,Any>()
            foodHashMap.put("kisiSayisi",kisiSayisi)
            foodHashMap.put("tarih",tarih)
            foodHashMap.put("anayemek",anayemek)
            foodHashMap.put("icecek",icecek)
            foodHashMap.put("tatli",tatli)
            foodHashMap.put("meyve",meyve)
            foodHashMap.put("fiyat",fiyat)

            store.collection("Foods").add(foodHashMap).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Kayıt oluşturuldu", Toast.LENGTH_LONG).show()
                    val intent= Intent(this,AdminGirisSayfasi::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener {
                Toast.makeText(applicationContext,it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(applicationContext,"Bütün alanları doldurun!", Toast.LENGTH_LONG).show()
        }

    }
    fun listeyeDon(view:View){
        val intent= Intent(this,AdminGirisSayfasi::class.java)
        startActivity(intent)
        finish()
    }
}