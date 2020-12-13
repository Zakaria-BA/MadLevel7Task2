package com.example.madlevel7task2.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.madlevel7task2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizViewModel : QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startQuiz.setOnClickListener { navigateUser() }
        quizViewModel.getQuiz()

        quizViewModel.quiz.observe(this, Observer {
            if (it.isEmpty()){
                startQuiz.isClickable = false
                Toast.makeText(this, quizViewModel.errorText.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun navigateUser(){
        startActivity(Intent(this, QuizActivity::class.java))
    }
}