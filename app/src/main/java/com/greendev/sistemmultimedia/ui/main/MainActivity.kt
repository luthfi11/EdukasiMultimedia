package com.greendev.sistemmultimedia.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.util.CourseListUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DashboardAdapter(CourseListUtil.course)

        rvDashboard.layoutManager = LinearLayoutManager(this)
        rvDashboard.adapter = adapter
    }
}
