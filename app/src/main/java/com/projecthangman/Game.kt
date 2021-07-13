package com.projecthangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import kotlin.random.Random
import java.util.*
import android.view.Window
import android.view.WindowManager

class Game : AppCompatActivity() {

    val arrayofTebakan = arrayListOf<String>()
    val jawabanSungguhan = arrayListOf<String>()
    val soal = arrayOf(themeDifficult("foods","easy",arrayOf("steak","burger","hotdog")),
            themeDifficult("foods","medium",arrayOf("pfeffernuss","cappelletti","pizzicato")),
            themeDifficult("foods","hard",arrayOf("lebkuchen","maraschino","chaudfroid")),
            themeDifficult("things","easy",arrayOf("computer","marker","smartphone")),
            themeDifficult("things","medium",arrayOf("ambulance","stereotype","harddrive")),
            themeDifficult("things","hard",arrayOf("xylophone","lawrensium","kromium")),
            themeDifficult("animals","easy",arrayOf("tiger","bison","lemur")),
            themeDifficult("animals","medium",arrayOf("butterfly","cockatoo","alligator")),
            themeDifficult("animals","hard",arrayOf("anthecinus","rattlesnake","porcupince")))
    var rst = 0
    var hasil = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val intent = this.intent
        val diff = intent.getStringExtra("diff")
        val name = intent.getStringExtra("playerName")
        val theme = intent.getStringExtra("theme")
        val gmbr = intent.getStringExtra("gambar")
        textViewName.text = "Hello $name"
        textViewDiff.text = "Difficulty : $diff"
        textViewTheme.text = "Theme : $theme"
        tvGambar.text = "$gmbr"
        if(tvGambar.text == "nampak"){
            imageView.isVisible = true
        }else if(tvGambar.text =="tidakNampak"){
            imageView.isVisible = false
        }
        var jawaban = ""
        for(items in soal){
            if(items.theme == theme && items.difficult == diff){
                var rnd = (0 until items.array.size).random()
                var guess = items.array[rnd]
                // isi karakter yang harus dijawab
                guess.forEach { arrayofTebakan.add(it.toString()) }
                // isi karakter yang ditampilkan user atau soal untuk user
                arrayofTebakan.forEach{jawabanSungguhan.add("_")}
            }
        }
        for (i in 0 until jawabanSungguhan.size){
            //untuk nampilkan jawaban di textView ketika sesuai jawaban
            jawaban += jawabanSungguhan[i]+" "
        }
        textViewTebakan.text = jawaban
        cekPencet(buttonA)
        cekPencet(buttonB)
        cekPencet(buttonC)
        cekPencet(buttonD)
        cekPencet(buttonE)
        cekPencet(buttonF)
        cekPencet(buttonG)
        cekPencet(buttonH)
        cekPencet(buttonI)
        cekPencet(buttonJ)
        cekPencet(buttonK)
        cekPencet(buttonL)
        cekPencet(buttonM)
        cekPencet(buttonN)
        cekPencet(buttonO)
        cekPencet(buttonP)
        cekPencet(buttonQ)
        cekPencet(buttonR)
        cekPencet(buttonS)
        cekPencet(buttonT)
        cekPencet(buttonU)
        cekPencet(buttonV)
        cekPencet(buttonW)
        cekPencet(buttonX)
        cekPencet(buttonY)
        cekPencet(buttonZ)
    }
    fun sendMenang(menang:Boolean, jawaban:String, score:Int){
        var rslts = ""

        var intents = Intent(this,result::class.java)
        if(!menang){
            rslts = "Yah kamu kalah~~" +
                    "jawabannya adalah" + jawaban
        }else{
            rslts = "Yay you win!" +
                    "Jawabanna "+jawaban
        }
        intents.putExtra("rslts",rslts)
        //intents.putExtra("scores",hasil)
        intents.putExtra("nilai",score)
        startActivity(intents)
    }
    fun cekPencet(button: Button) {
        button.setOnClickListener {
            it as Button
            var jawaban = ""
            var cek = false
            // looping untuk pengecekan pencetan button
            for (i in 0 until arrayofTebakan.size) {
                //apabila button sesuai dengan array, langsung masuk ke jawabanSungguhan
                if (arrayofTebakan[i].equals(it.text.toString(), true)) {
                    jawabanSungguhan[i] = arrayofTebakan[i]
                    cek = true
                }
            }
            //melakukan perhitungan kesalahan
            if(cek == false){
                rst++
                when (rst) {
                    1 -> imageView.setImageResource(R.drawable.hangman1)
                    2 -> imageView.setImageResource(R.drawable.hangman2)
                    3 -> imageView.setImageResource(R.drawable.hangman3)
                    4 -> imageView.setImageResource(R.drawable.hangman4)
                    5 -> imageView.setImageResource(R.drawable.hangman5)
                    6 -> imageView.setImageResource(R.drawable.hangman6)
                    7 -> imageView.setImageResource(R.drawable.hangman7)
                    8 -> imageView.setImageResource(R.drawable.hangman8)
                    9 -> imageView.setImageResource(R.drawable.hangman9)
                    10 -> imageView.setImageResource(R.drawable.hangman10)
                    11 -> {
                        imageView.setImageResource(R.drawable.hangman11)
                        sendMenang(false,arrayofTebakan.toString(),rst)
                    }
                }
//                var score = (11 - rst) * 10
//                hasil = score.toString()
            }
            for (i in 0 until jawabanSungguhan.size) {
                //untuk nampilkan jawaban di textview ketika button dipencet
                jawaban += jawabanSungguhan[i] + " "
            }
            if(!jawabanSungguhan.contains("_")){
                sendMenang(true,arrayofTebakan.toString(),rst)
            }
            textViewTebakan.text = jawaban
            it.isEnabled = false
        }
    }
}
