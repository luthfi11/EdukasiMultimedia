package com.greendev.sistemmultimedia.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greendev.sistemmultimedia.data.model.Quiz

class QuizViewModel : ViewModel() {

    val quiz = MutableLiveData<Quiz>()
    val score by lazy {
        MutableLiveData<Int>().also { it.value = 0 }
    }

    fun addScore() {
        score.postValue(score.value?.plus(20))
    }
}