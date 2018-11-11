package com.example.ytpillai.cmsc_355_proj.messaging

import android.app.Application

class App:Application() {
    companion object {
        lateinit var nickname:String
        lateinit var ip:String
    }
}