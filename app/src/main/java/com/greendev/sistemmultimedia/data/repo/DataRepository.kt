package com.greendev.sistemmultimedia.data.repo

import android.content.Context
import com.google.gson.Gson
import com.greendev.sistemmultimedia.data.model.Lesson
import com.greendev.sistemmultimedia.data.model.LessonResponse

class DataRepository {

    fun getCourse(context: Context, id: String?): List<Lesson> {
        val jsonFile = context.assets.open("courseTest.json").bufferedReader().use { it.readText() }
        val lesson = Gson().fromJson(jsonFile, LessonResponse::class.java)
        return lesson.course.filter { it.courseId == id}
    }

    fun getQuiz() {

    }
}