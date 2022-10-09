package com.example.laba_14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BreakingBad : AppCompatActivity() {
    var MainText = ""
    private lateinit var button: Button
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    var index: Int = 0
    var onecheck = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breaking_bad)
        this.supportActionBar!!.hide()
        button = findViewById(R.id.button)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        var quote_id: String = ""
        var quote: String = ""
        var author: String = ""
        var series: String = ""

        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        button.setOnClickListener {
            button.text = "следующий элемент"
            if (onecheck == 0) {
                MainText = executorService.submit(Callable {
                    httpRequest("https://www.breakingbadapi.com/api/quotes")
                }).get()
            }
            onecheck++
            var i: Int = 0
            var chek = 0
            var chek2 = 0
            var chek3 = 0
            var i2: Int = 0
            var lastcheck = 0
            MainText.forEach {
                i++
                if (chek == 0) {
                    quote_id += it
                }
                if (chek == 1) {
                    quote += it
                }
                if (chek == 2) {
                    author += it
                }

                if (chek == 3) {
                    series += it
                }
                if (chek == 4 && lastcheck == 0) {
                    i2 = i
                    lastcheck++
                }
                if (it == ',' && chek == 0) {
                    chek++
                }
                if (it == '"') {
                    Log.d("check01", "$chek $index $i")
                    chek2++
                    if (chek2 == 2 && chek3 == 0) {
                        chek2 = 0
                        chek3++
                    }
                    if (chek2 == 4 && chek3 > 0) {
                        chek++
                        chek2 = 0
                    }
                }


            }
            var d = "dasd"
            MainText = MainText.removeRange(0, i2 + 1)

            textView1.text = quote_id
            textView2.text = quote
            textView3.text = author
            textView4.text = series
            quote_id = ""
            quote = ""
            author = ""
            series = ""
        }
    }

    @Throws(IOException::class)
    fun httpRequest(urlString: String): String {
        /*Созддание экземпляра URL*/
        val url = URL(urlString)
        /*Создание экзепляра класса для соедниения по протоколу HTTP*/
        val connection = url.openConnection() as HttpURLConnection
        /*Установка метода запроса*/
        connection.requestMethod = "GET"
        /*Отправка запроса и чтение полученых данных*/
        var data: Int = connection.inputStream.read()
        var str = ""
        while (data != -1) {
            str += data.toChar()
            data = connection.inputStream.read()
        }
        Log.d("API_QWE", str)
        return str
    }
}