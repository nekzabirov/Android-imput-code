package com.nikita.codeedittext

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        codeedittext.onListener = {correct ->
            //Detect input
        }

        //Set code
        codeedittext.text = "123456"

        codeedittext.color = Color.BLACK

        codeedittext.textColor = Color.BLACK

        //Get code
        val code = codeedittext.text

    }
}
