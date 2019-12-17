package com.greendev.sistemmultimedia.ui.lesson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greendev.sistemmultimedia.data.model.Lesson

class LessonViewModel: ViewModel() {

    val course = MutableLiveData<Lesson>()
}