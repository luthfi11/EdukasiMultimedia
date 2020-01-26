package com.greendev.sistemmultimedia.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greendev.sistemmultimedia.data.model.Quiz

class QuizViewModel : ViewModel() {

    val quiz = MutableLiveData<Quiz>()
}