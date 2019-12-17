package com.greendev.sistemmultimedia.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.LessonResponse

class QuizActivity : AppCompatActivity() {

    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //val data = gson.fromJson(assets.open("courseTest.json").toString(), LessonResponse::class.java)
        //Log.d("AAAAAAAAAAAAAAA", "${assets.open("courseTest.json").bufferedReader().use { it.readText() }}")
    }
}
