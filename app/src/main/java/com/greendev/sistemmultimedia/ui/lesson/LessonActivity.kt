package com.greendev.sistemmultimedia.ui.lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.greendev.sistemmultimedia.R
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        setSupportActionBar(toolbarLesson)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val courseName = intent?.getStringExtra("course")

        toolbarLesson.title = courseName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
