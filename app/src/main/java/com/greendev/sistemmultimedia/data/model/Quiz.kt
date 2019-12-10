package com.greendev.sistemmultimedia.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    var courseId: String?,
    var questionNumber: Int?,
    var question: String?,
    var optionA: String?,
    var optionB: String?,
    var optionC: String?,
    var optionD: String?,
    var answer: Char?
): Parcelable