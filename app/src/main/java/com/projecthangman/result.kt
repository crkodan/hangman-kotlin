package com.projecthangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import android.view.Window
import android.view.WindowManager

class result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val int = this.intent
        val ucapan = int.getStringExtra("rslts")
        val scr = int.getIntExtra("nilai", 0)
        textViewUcapan.text = ucapan
        var score = (11 - scr) * 10
        textViewResult.text = "total score: $score"

        btnHome.setOnClickListener{
            val intss = Intent(this,MainActivity::class.java)
            startActivity(intss)
        }
    }
}
