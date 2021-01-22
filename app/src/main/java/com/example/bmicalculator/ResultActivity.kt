package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ResultActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById<TextView>(R.id.resultTextView)
        imageView = findViewById<ImageView>(R.id.imageView)
        var name = intent.getStringExtra("name")
        var height = intent.getStringExtra("height")!!.toInt()
        var weight = intent.getStringExtra("weight")!!.toInt()

        //BMI 계산
        var bmi = weight/Math.pow(height/100.0, 2.0)

        //BMI 결과에 따라 텍스트 출력
        when{
            bmi>=35->resultTextView.text = "고도 비만"
            bmi>=30->resultTextView.text = "2단계 비만"
            bmi>=25->resultTextView.text = "1단계 비만"
            bmi>=23->resultTextView.text = "과체중"
            bmi>=18.5->resultTextView.text = "정상"
            else->resultTextView.text = "저체중"
        }

        //BMI 결과에 따라 그림 출력
        when{
            bmi>=23->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_dissatisfied_24
                )
            bmi>=18.5->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_satisfied_alt_24
                )
            else->
                imageView.setImageResource(
                    R.drawable.ic_baseline_mood_bad_24
                )
        }
        
        //토스트 : 하단에 잠깐 보였다가 사라지는 메시지
        //bmi 결과를 잠깐 보이게 하기
        Toast.makeText(this, "$name : $bmi", Toast.LENGTH_SHORT).show()
    }
}