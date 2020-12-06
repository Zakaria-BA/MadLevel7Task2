package com.example.madlevel7task2.ui

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel7task2.model.Quiz
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val _quiz: MutableLiveData<ArrayList<Quiz>> = MutableLiveData()
    private var database: DatabaseReference = Firebase.database.reference.child("quiz")
    private lateinit var quizListener: ValueEventListener

    val quiz: LiveData<ArrayList<Quiz>>
        get() = _quiz

    fun getQuiz() {
    }
}