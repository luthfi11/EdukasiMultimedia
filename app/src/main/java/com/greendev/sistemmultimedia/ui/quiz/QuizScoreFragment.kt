package com.greendev.sistemmultimedia.ui.quiz


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.greendev.sistemmultimedia.R
import kotlinx.android.synthetic.main.fragment_quiz_score.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * A simple [Fragment] subclass.
 */
class QuizScoreFragment : Fragment() {

    companion object {
        fun newInstance(score: Int): QuizScoreFragment {
            val fragment = QuizScoreFragment()

            val bundle = Bundle().apply {
                putInt("score", score)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_score, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = arguments?.getInt("score")

        Glide.with(context!!).asGif().load(R.drawable.ic_result).into(imgResult)
        tvScore.text = "Skor Kamu : $score"

        btnPlayAgain.onClick {
            activity?.finish()
        }
    }

}
