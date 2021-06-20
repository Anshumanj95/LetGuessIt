package com.example.letsguessit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.letsguessit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val totalTurns=5
    private var number=getRandomNumber()
    private var remainingTurns=totalTurns
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("mylog",number.toString())
        binding.button.setOnClickListener { check() }
        binding.reset.setOnClickListener{reset()}
    }
    private fun getRandomNumber():Int=(1..100).random()
    private fun check(){
        if(--remainingTurns<=0){
            val user = binding.input.text.toString().toInt()
            binding.answer.text = if (user == number) {
                "Congratulation! Yow Win this.\nYou take ${totalTurns-remainingTurns} turns to find it.\nThe correct number is $user"
            }else {
                "You Lost the game.\nThe correct number is $number.\nRestart the game."
            }
        }
        else {
            val user = binding.input.text.toString().toInt()
            binding.answer.text = when {
                user == number -> {
                    "Congratulation! Yow Win this.\nYou take ${totalTurns-remainingTurns} turns to find it.\nThe correct number is $user"
                }
                user > number -> {
                    "Too high.. $remainingTurns turns are left"
                }
                else -> {
                    "Too low..  $remainingTurns  turns are left"
                }
            }
        }
        binding.input.text.clear()
    }
    @SuppressLint("SetTextI18n")
    private fun reset(){
        number=getRandomNumber()
        binding.answer.text="Game is restart"
        remainingTurns=totalTurns
        Log.d("mylog", number.toString())

    }

}