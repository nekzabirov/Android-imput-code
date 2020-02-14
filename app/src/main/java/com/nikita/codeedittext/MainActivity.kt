package com.nikita.codeedittext

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

        //Get code
        val code = codeedittext.text

    }
}
