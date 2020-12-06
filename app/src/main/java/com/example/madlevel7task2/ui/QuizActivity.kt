package com.example.madlevel7task2.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.madlevel7task2.R
import com.example.madlevel7task2.model.Quiz
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val quizViewModel : QuizViewModel by viewModels()
    private val quiz = ArrayList<Quiz>()
    private lateinit var answer: String
    private var qurrentQuisten = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        observeQuiz()
        initViews()
    }

    private fun initViews(){
        tvVraag.text = quiz[0].vraag
        radio_1.text = quiz[0].antwoorden[0]
        radio_2.text = quiz[0].antwoorden[1]
        radio_3.text = quiz[0].antwoorden[2]
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_1 ->
                    if (checked) {
                        answer = radio_1.text.toString()
                    }
                R.id.radio_2 ->
                    if (checked) {
                        answer = radio_2.text.toString()
                    }
                R.id.radio_3 -> {
                    if (checked) {
                        answer = radio_3.text.toString()
                    }
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