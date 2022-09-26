package com.user.yemekhanesistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_besin_detayi.*

class BesinDetayi : AppCompatActivity() {

    private lateinit var store:FirebaseFirestore
    private lateinit var saayisi:TextView
    private lateinit var tarih:TextView
    private lateinit var yemek:TextView
    private lateinit var icecek:TextView
    private lateinit var tatli:TextView
    private lateinit var meyve:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_besin_detayi)
        store = FirebaseFirestore.getInstance()

        val foodIntent = intent
        val foodId = foodIntent.getStringExtra("txt_pkisiSayisi")
        val foodTarih = foodIntent.getStringExtra("txt_ptarih")
        val foodName = foodIntent.getStringExtra("txt_panayemek")
        val foodIcecek = foodIntent.getStringExtra("txt_picecek")
        val foodTatli = foodIntent.getStringExtra("txt_ptatli")
        val foodMeyve = foodIntent.getStringExtra("txt_pmeyve")
        val foodFiyat = foodIntent.getStringExtra("txt_pfiyat")

        txt_pkisiSayisi.text = foodId
        txt_ptarih.text = foodTarih
        txt_panayemek.text = foodName
        txt_picecek.text = foodIcecek
        txt_ptatli.text = foodTatli
        txt_pmeyve.text = foodMeyve
        txt_pfiyat.text = foodFiyat
    }
    fun yemekIstek(view:View){
        val checkyemek=onayverme.isChecked
        saayisi=findViewById(R.id.txt_pkisiSayisi)
        tarih=findViewById(R.id.txt_ptarih)
        yemek=findViewById(R.id.txt_panayemek)
        icecek=findViewById(R.id.txt_picecek)
        tatli=findViewById(R.id.txt_ptatli)
        meyve=findViewById(R.id.txt_pmeyve)



        if(checkyemek==true) {
            val onaySayisi=saayisi.text.toString()
            saayisi.setText((onaySayisi.toInt()+1).toString())
            update(onaySayisi)
            Toast.makeText(this,"Yemek yeme isteğiniz admine iletilmiştir.",Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(applicationContext,"Yemek yemek isterseniz yandaki kutucuğu işaretleyiniz.",Toast.LENGTH_SHORT).show()

        }

    }

    fun update(onaySayisi: String){

        var tarih=tarih.text.toString()
        val yemek=yemek.text.toString()
        val icecek=icecek.text.toString()
        val tatli=tatli.text.toString()
        val meyve =meyve.text.toString()
        store.collection("Foods").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents
                        for(document in documents){
                            var k=document.id
                            var t=document.get("tarih")
                            var y=document.get("anayemek")
                            val i=document.get("icecek")
                            val tat=document.get("tatli")
                            val m=document.get("meyve")
                            if(tarih==t && yemek==y && icecek==i && tatli==tat && meyve==m){
                                store.collection("Foods").document(k).update("kisiSayisi",(onaySayisi.toInt()+1).toString())
                            }else{

                            }

                        }}}}
        }
    }


    fun listeyeDon(view: View)
    {
       val intent=Intent(this,PersonelGirisSayfasi::class.java)
       startActivity(intent)
       finish()
    }
    }

