package com.example.laba_14

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var meat_button: Button
    private lateinit var hate_button: Button
    private lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.hide()
        meat_button = findViewById(R.id.meat_button)
        meat_button.setOnClickListener{
            val intent = Intent(this@MainActivity,meat_activity::class.java)
            startActivity(intent)

        }
        hate_button = findViewById(R.id.hate_button)
        hate_button.setOnClickListener{
            val intent = Intent(this@MainActivity,hate_activiti::class.java)
            startActivity(intent)

        }
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent = Intent(this@MainActivity,BreakingBad::class.java)
            startActivity(intent)

        }

    }

}