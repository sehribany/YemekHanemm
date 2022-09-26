package com.user.yemekhanesistemi

class Users{
    var kullaniciIsim:String?=null
    var kullaniciEmail:String?=null
    var kullaniciPassword:String?=null
    var adminmi:Boolean?=null
    var personelmi:Boolean?=null
    constructor(){}
    constructor(
        kullaniciIsim:String?,
        kullaniciEmail:String?,
        kullaniciPassword:String?,
        adminmi:Boolean?,
        personelmi:Boolean?,


    ){
        this.kullaniciIsim=kullaniciIsim
        this.kullaniciEmail=kullaniciEmail
        this.kullaniciPassword=kullaniciPassword
        this.adminmi=adminmi
        this.personelmi=personelmi
    }





}