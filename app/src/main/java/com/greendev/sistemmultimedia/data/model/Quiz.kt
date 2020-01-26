package com.greendev.sistemmultimedia.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class QuizResponse(var quiz: List<Quiz>)

@Parcelize
data class Quiz(
    var courseId: String?,
    var imgLink: String?,
    var question: String?,
    var optionA: String?,
    var optionB: String?,
    var optionC: String?,
    var optionD: String?,
    var answer: Char?
): Parcelable