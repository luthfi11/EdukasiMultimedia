package com.greendev.sistemmultimedia.ui.main

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.CourseCategory
import com.greendev.sistemmultimedia.ui.lesson.LessonActivity
import kotlinx.android.synthetic.main.item_dashboard.view.*
import kotlinx.android.synthetic.main.layout_popup.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class DashboardAdapter(private val courseCategory: List<CourseCategory>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dashboard,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return courseCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseCategory[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(courseCategory: CourseCategory) {
            with(itemView) {
                imgBg.imageResource = courseCategory.background
                tvCourse.text = courseCategory.courseName

                onClick {
                    showDialog(courseCategory.courseName)
                }
            }
        }

        private fun showDialog(courseName: String) {
            val mDialogView =
                LayoutInflater.from(itemView.context).inflate(R.layout.layout_popup, null)
            AlertDialog.Builder(itemView.context)
                .setView(mDialogView)
                .show()

            with(itemView.context) {
                mDialogView.vLesson.onClick { startActivity<LessonActivity>("course" to courseName) }
                mDialogView.vQuiz.onClick { }
            }
        }
    }
}