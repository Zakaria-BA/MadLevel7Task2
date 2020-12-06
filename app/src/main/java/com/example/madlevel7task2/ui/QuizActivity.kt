package com.example.madlevel7task2.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.madlevel7task2.R
import com.example.madlevel7task2.model.Quiz

class QuizActivity : AppCompatActivity() {
    private val quizViewModel : QuizViewModel by viewModels()
    private val quiz = ArrayList<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        observeQuiz()
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_1 ->
                    if (checked) {
                        // Pirates are the best
                    }
                R.id.radio_2 ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }

    fun onSubmitButtonClicked(view: View){

    }

    private fun observeQuiz(){
        quizViewModel.quiz.observe(this, Observer {
            this@QuizActivity.quiz.addAll(it)
        })
    }
}