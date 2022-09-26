package com.user.yemekhanesistemi

import java.lang.reflect.Constructor

class Foods{
    var kisiSayisi :String?=null
    var tarih:String?=null
    var anayemek:String?=null
    var icecek:String?=null
    var tatli:String?=null
    var meyve:String?=null
    var fiyat:String?=null
    constructor(){}
    constructor(
        kisiSayisi:String?,
        tarih:String?,
        anayemek:String?,
        icecek:String?,
        tatli:String?,
        meyve:String?,
        fiyat:String?

    ){
        this.kisiSayisi=kisiSayisi
        this.tarih=tarih
        this.anayemek=anayemek
        this.icecek=icecek
        this.tatli=tatli
        this.meyve=meyve
        this.fiyat=fiyat
    }
}
