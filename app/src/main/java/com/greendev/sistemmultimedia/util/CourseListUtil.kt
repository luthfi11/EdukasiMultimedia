package com.greendev.sistemmultimedia.util

import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.CourseCategory

object CourseListUtil {
    val course = listOf(
        CourseCategory("Sejarah", R.drawable.ic_sejarah),
        CourseCategory("Geografis", R.drawable.ic_geografis),
        CourseCategory("Seni dan Budaya", R.drawable.ic_seni),
        CourseCategory("Kuliner", R.drawable.ic_kuliner),
        CourseCategory("Fashion", R.drawable.ic_fashion),
        CourseCategory("Wisata", R.drawable.ic_wisata)
    )
}