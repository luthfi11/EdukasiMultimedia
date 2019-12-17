package com.greendev.sistemmultimedia.ui.main

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greendev.sistemmultimedia.R
import com.greendev.sistemmultimedia.data.model.Category
import com.greendev.sistemmultimedia.ui.lesson.LessonActivity
import com.greendev.sistemmultimedia.ui.quiz.QuizActivity
import kotlinx.android.synthetic.main.item_dashboard.view.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_popup.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class DashboardAdapter(private val category: List<Category>) :
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
        return category.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position])
        setAnimation(holder.itemView, position)
    }

    private var lastPosition = -1
    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = if (position % 2 == 1) {
                AnimationUtils.loadAnimation(
                    viewToAnimate.context,
                    R.anim.slide_out_left
                )
            } else {
                AnimationUtils.loadAnimation(
                    viewToAnimate.context,
                    R.anim.slide_in_left
                )
            }
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            with(itemView) {
                imgBg.imageResource = category.background
                tvCourse.text = category.courseName

                onClick {
                    showDialog(category.courseName)
                }
            }
        }

        private fun showDialog(courseName: String) {
            val mDialogView =
                LayoutInflater.from(itemView.context).inflate(R.layout.layout_popup, null)

            val dialog = Dialog(itemView.context).apply {
                setContentView(mDialogView)
                window?.setBackgroundDrawableResource(R.drawable.shape_card_dark)
            }

            dialog.show()

            with(itemView.context) {
                mDialogView.vLesson.onClick {
                    dialog.dismiss()
                    startActivity<LessonActivity>("course" to courseName)
                }
                mDialogView.vQuiz.onClick { startActivity<QuizActivity>() }
            }
        }

        private fun loadingDialog(): Dialog {
            val dialog = Dialog(itemView.context).apply {
                setContentView(R.layout.layout_loading)
            }
            Glide.with(itemView.context).asGif().load(R.drawable.loading).into(dialog.imgLoad)
            return dialog
        }
    }
}