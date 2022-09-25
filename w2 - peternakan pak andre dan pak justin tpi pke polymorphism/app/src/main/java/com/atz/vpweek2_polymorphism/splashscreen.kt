package com.atz.vpweek2_polymorphism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()
        Handler().postDelayed({

            val splashz = Intent(this@splashscreen, MainActivity::class.java)
            startActivity(splashz)
            finish()
        },3000)
    }
}