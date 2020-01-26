package com.greendev.sistemmultimedia.ui.quiz

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.Quiz
import com.greendev.sistemmultimedia.data.repo.DataRepository
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.layout_popup_quiz.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.textColorResource

class QuizActivity : AppCompatActivity() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var dataRepository: DataRepository
    private lateinit var filteredQuiz: List<Quiz>
    private lateinit var dialog: Dialog
    private var score = 0
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        setSupportActionBar(toolbarQuiz)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val courseName = intent?.getStringExtra("course")
        toolbarQuiz.title = "Quiz $courseName"

        dataRepository = DataRepository()
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        filteredQuiz = dataRepository.getQuiz(this, courseName)

        viewModel.quiz.postValue(filteredQuiz[i])
        viewModel.quiz.observe(this, Observer {
            with(filteredQuiz[i]) {
                if (imgLink != "") {
                    imgQuiz.visibility = View.VISIBLE
                    Glide.with(applicationContext).load(imgLink).into(imgQuiz)
                } else imgQuiz.visibility = View.GONE

                tvQuestion.text = question
                btnA.text = optionA
                btnB.text = optionB
                btnC.text = optionC
                btnD.text = optionD

                choice(answer)
            }
        })

        initDialog()
        initTransition()
    }

    private fun choice(answer: Char?) {
        when (answer) {
            'A' -> setAnswer(btnA, btnB, btnC, btnD)
            'B' -> setAnswer(btnB, btnA, btnC, btnD)
            'C' -> setAnswer(btnC, btnA, btnB, btnD)
            'D' -> setAnswer(btnD, btnC, btnB, btnA)
        }
    }

    private fun setAnswer(vararg btn: Button) {
        btn.forEachIndexed { index, button ->
            if (index == 0) button.onClick {
                showTrue()
            }
            else button.onClick { showWrong() }
        }
    }

    private suspend fun showTrue() {
        score += 20
        showPopup("+20", R.color.colorGreen, R.drawable.ic_true)

    }

    private suspend fun showWrong() {
        showPopup("+0", android.R.color.darker_gray, R.drawable.ic_wrong)
    }

    private fun showNewData() {
        if (i < filteredQuiz.size - 1) {
            i++
            viewModel.quiz.postValue(filteredQuiz[i])
        } else {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, QuizScoreFragment.newInstance(score)).commit()
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun showPopup(score: String, color: Int, icon: Int) {
        Glide.with(this).asGif().load(icon).into(dialog.imgTrue)
        dialog.tvAddMin.text = score
        dialog.tvAddMin.textColorResource = color
        dialog.tvScore.text = "Skor Kamu : ${this.score}"
        dialog.show()
        delay(1000)
        dialog.dismiss()
        showNewData()
    }

    private fun initDialog() {
        dialog = Dialog(this).apply {
            setContentView(R.layout.layout_popup_quiz)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setCancelable(false)
        }
    }

    private fun initTransition() {
        val animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        content.startAnimation(animation)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
