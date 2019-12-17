package com.greendev.sistemmultimedia.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LessonResponse (var course: List<Lesson>)

@Parcelize
data class Lesson(
    var courseId: String?,
    var page: Int?,
    var title: String?,
    var content: String?,
    var imgLink: String?,
    var videoLink: String?
): Parcelable