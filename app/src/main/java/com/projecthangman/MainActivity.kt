package com.projecthangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.spinner
import org.jetbrains.anko.startActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_game.*
import android.view.Window
import android.view.WindowManager


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var defdif = "easy"
        var gambar = ""
        val theme = arrayOf("foods","things","animals")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,theme)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTheme.adapter = adapter
        groupTipeTheme.setOnCheckedChangeListener{group,id->
            var diff = when(id){
                R.id.rbEasy -> defdif = "easy"
                R.id.rbHard -> defdif = "hard"
                R.id.rbMedium -> defdif = "medium"
                else ->""
            }
            Toast.makeText(applicationContext,defdif,Toast.LENGTH_LONG).show()
        }
        fun onCheckboxClicked(view : View){
            view as CheckBox
            when(view.id){
                R.id.cbDisplayHangman -> if(view.isChecked){
                    gambar = "nampak"
                }else{
                    gambar = "tidakNampak"
                }
            }
        }
        buttonStart.setOnClickListener{
            var intent = Intent(this,Game::class.java)
            val name = editTextName.text.toString()
            intent.putExtra("playerName",name)
            val diff = defdif
            intent.putExtra("diff",diff)
            val theme = spinnerTheme.selectedItem.toString()
            intent.putExtra("theme",theme)
            intent.putExtra("gambar",gambar)
            startActivity(intent)
        }
    }

}
