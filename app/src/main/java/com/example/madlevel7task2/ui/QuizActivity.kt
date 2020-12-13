package com.example.madlevel7task2.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.madlevel7task2.R
import com.example.madlevel7task2.model.Quiz
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val quizViewModel : QuizViewModel by viewModels()
    private val quiz = ArrayList<Quiz>()
    private lateinit var answer: String
    private var qurrentQuisten = 0
    private var quizLength = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quizViewModel.getQuiz()
        observeQuiz()
    }

    private fun initViews(){
        tvVraag.text = quiz.get(qurrentQuisten).vraag

        radio_1.text = quiz.get(qurrentQuisten).antwoorden.get(0)
        radio_2.text = quiz.get(qurrentQuisten).antwoorden.get(1)
        radio_3.text = quiz.get(qurrentQuisten).antwoorden.get(2)

        quizQuestionsNr.text = (qurrentQuisten + 1).toString() + "/" + quizLength.toString()
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
        if (answer == quiz.get(qurrentQuisten).goeieAntwoord){
            if (qurrentQuisten + 1 == quizLength){
                Toast.makeText(this, "Quest completed", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                qurrentQuisten++
                Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT).show()
                initViews()
            }
        } else {
            Toast.makeText(this, "Your answer is wrong!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun observeQuiz(){
        quizViewModel.quiz.observe(this, Observer {
            this@QuizActivity.quiz.clear()
            this@QuizActivity.quiz.addAll(it)
            quizLength = quiz.size
            tvVraag.text = quiz.get(qurrentQuisten).vraag

            radio_1.text = quiz.get(qurrentQuisten).antwoorden.get(0)
            radio_2.text = quiz.get(qurrentQuisten).antwoorden.get(1)
            radio_3.text = quiz.get(qurrentQuisten).antwoorden.get(2)
            quizQuestionsNr.text = (qurrentQuisten + 1).toString() + "/" + quizLength.toString()
            pb_loading_quiz.isVisible = false
            csLayoutQuiz.isVisible = true
        })
    }
}