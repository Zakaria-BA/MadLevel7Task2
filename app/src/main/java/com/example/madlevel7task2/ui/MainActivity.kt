package com.example.madlevel7task2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.madlevel7task2.R

class MainActivity : AppCompatActivity() {

    private val quizViewModel : QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun observeQuiz(){
        quizViewModel.getQuiz()
    }
}