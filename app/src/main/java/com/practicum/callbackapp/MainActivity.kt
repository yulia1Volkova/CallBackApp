package com.practicum.callbackapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random

class MainActivity() : AppCompatActivity(), LuckCallBack {
    private var number: Int = -1
    private lateinit var luckTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNum = RandomNum()


        luckTextView = findViewById<TextView>(R.id.luckTextView)
        val luckEditView = findViewById<EditText>(R.id.dataEditText)
        val luckButton = findViewById<Button>(R.id.luckButton)

        luckButton.setOnClickListener {
            number = luckEditView.text.toString().toInt()
            randomNum.getNumberOfAttempts(number, this)
        }

    }

    override fun onSuccess(counter: Int, enteredNumber: Int) {
        println("Рандомайзер выдал ваше число $enteredNumber с $counter попытки")
        luckTextView.text = "Рандомайзер выдал ваше число $enteredNumber с $counter попытки"
    }
}


class RandomNum {
    fun getNumberOfAttempts(enteredNumber: Int, callBack: LuckCallBack) {
        var counter: Int = 0
        for (i in 1..10000000) {
            val numRandom = Random.nextInt(1, 1000000)
            if (enteredNumber != numRandom)
                counter++
            else {
                callBack.onSuccess(counter, enteredNumber)
            }
        }
    }
}

interface LuckCallBack {
    fun onSuccess(counter: Int, enteredNumber: Int)
}
/*class LuckHandler: LuckCallBack{
    var message=""
    override fun onSuccess(counter: Int,enteredNumber: Int){
        println("Рандомайзер выдал ваше число $enteredNumber с $counter попытки")
       message="Рандомайзер выдал ваше число $enteredNumber с $counter попытки"
    }
}*/
