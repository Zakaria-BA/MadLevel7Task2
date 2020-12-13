package com.example.madlevel7task2.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.madlevel7task2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizViewModel : QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startQuiz.setOnClickListener { navigateUser() }
    }

    private fun navigateUser(){
        Log.d(ContentValues.TAG, "We gaan beginnen")
        quizViewModel.getQuiz()
        Log.d(ContentValues.TAG, "Done")

//        startActivity(Intent(this, QuizActivity::class.java))
    }
}