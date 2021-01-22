package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

//첫번째 화면
class MainActivity : AppCompatActivity() {
    //변수 선언
    lateinit var resultButton : Button
    lateinit var nameEditText: EditText
    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //첫번째 화면에서 결과 화면으로 전환하는 코드 - 인턴트 사용

        //Button 연결
        resultButton = findViewById<Button>(R.id.resultButton)

        //변수 연결
        nameEditText = findViewById<EditText>(R.id.nameEditText)
        heightEditText = findViewById<EditText>(R.id.HeightEditText)
        weightEditText = findViewById<EditText>(R.id.WeightEditText)

        //액티비티 시작하면 마지막에 저장한 값을 불러오기
        loadData()

        //Button을 눌렀을 때 실행
        resultButton.setOnClickListener{
            saveData(nameEditText.text.toString(), heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

            //지금 클래스에 대한 정보를 intent 변수에 넣어 ResultActivity로 넘기기
            var intent = Intent( this, ResultActivity::class.java)
            //intent 변수에 height, weight 변수 정보를 각각 추가
            intent.putExtra("name", nameEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
            intent.putExtra("weight", weightEditText.text.toString())
            

            //해당 intent 실행
            startActivity(intent)
        }
    }
    //받은 입력값을 저장하는 함수 생성
    private fun saveData(name:String, height:Int, weight:Int){
        //입력한 값 불러오기 - getPreferences이용(공유환경설정 파일 제공하는 메소드)
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        //editor에 키, 몸무게 값을 입력하기 - apply()이용
        //apply() - editor에 해당 값을 저장하는 기능
        editor.putString("KEY_NAME",
            nameEditText.text.toString()).apply()
        editor.putInt("KEY_HEIGHT",
            heightEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_WEIGHT",
            weightEditText.text.toString().toInt()).apply()
    }

    //데이터 불러오기
    private fun loadData(){
        var pref = this.getPreferences(0)
        //KEY가 각각 HEIGHT, WEIGHT인 값을 불러오기
        //defValue에는 해당 키가 존재하지 않을때 기본적으로 설정 할 값
        var name = pref.getString("KEY_NAME", "")
        var height = pref.getInt("KEY_HEIGHT", 0)
        var weight = pref.getInt("KEY_WEIGHT", 0)

        //height와 weight 값이 각각 0이 아니라면(null 값이 아니라면) 불러오기
        if(name != null && height != 0 && weight != 0){
            nameEditText.setText(name.toString())
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

}
