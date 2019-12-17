package com.greendev.sistemmultimedia.ui.lesson

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.Lesson
import com.greendev.sistemmultimedia.data.repo.DataRepository
import com.halilibo.bvpkotlin.BetterVideoPlayer
import com.halilibo.bvpkotlin.VideoCallback
import kotlinx.android.synthetic.main.activity_lesson.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class LessonActivity : AppCompatActivity() {

    private lateinit var viewModel: LessonViewModel
    private lateinit var dataRepository: DataRepository
    private lateinit var filteredLesson: List<Lesson>
    private var i = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        setSupportActionBar(toolbarLesson)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val courseName = intent?.getStringExtra("course")

        dataRepository = DataRepository()
        viewModel = ViewModelProviders.of(this).get(LessonViewModel::class.java)
        filteredLesson = dataRepository.getCourse(this, courseName)

        viewModel.course.postValue(filteredLesson[i])
        viewModel.course.observe(this, Observer {
            toolbarLesson.title = courseName
            tvTitle.text = filteredLesson[i].title
            tvContent.text = filteredLesson[i].content
            tvPage.text = "${i + 1}/${filteredLesson.size}"

            Glide.with(this).load(filteredLesson[i].imgLink)
                .placeholder(android.R.color.darker_gray)
                .into(imgContent)
            videoLesson.setSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
            videoLesson.setCallback(videoCallback)

            btnPrev.isEnabled = i >= 1
            btnNext.isEnabled = i != filteredLesson.size - 1
        })

        initTransition()
        onAction()
    }

    private fun onAction() {
        btnPrev.onClick {
            if (i > 0) {
                i--
                showNewData(filteredLesson[i])
                setTransition(R.anim.slide_in_left)
            }
        }

        btnNext.onClick {
            if (i < filteredLesson.size - 1) {
                i++
                showNewData(filteredLesson[i])
                setTransition(R.anim.slide_out_left)
            }
        }
    }

    private fun showNewData(lesson: Lesson) {
        viewModel.course.postValue(lesson)
        scrollView2.smoothScrollTo(0, 0)
    }

    private fun initTransition() {
        val animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        content.startAnimation(animation)
    }

    private fun setTransition(anim: Int) {
        val animation =
            AnimationUtils.loadAnimation(applicationContext, anim)
        imgContent.startAnimation(animation)
        tvTitle.startAnimation(animation)
        tvContent.startAnimation(animation)
        videoLesson.startAnimation(animation)

        val imgAnim =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        imgContent.startAnimation(imgAnim)
    }

    private val videoCallback = object : VideoCallback {

        override fun onStarted(player: BetterVideoPlayer) {

        }

        override fun onPaused(player: BetterVideoPlayer) {

        }

        override fun onPreparing(player: BetterVideoPlayer) {
            player.start()
        }

        override fun onPrepared(player: BetterVideoPlayer) {

        }

        override fun onBuffering(percent: Int) {

        }

        override fun onError(player: BetterVideoPlayer, e: Exception) {
            e.printStackTrace()
            Log.d("AAAAAAAAA", "${e.message}")
        }

        override fun onCompletion(player: BetterVideoPlayer) {

        }

        override fun onToggleControls(player: BetterVideoPlayer, isShowing: Boolean) {

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        videoLesson.pause()
    }
}
