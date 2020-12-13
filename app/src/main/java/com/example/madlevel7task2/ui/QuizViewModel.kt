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
import com.example.madlevel7task2.rrepository.QuizRepository
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository: QuizRepository = QuizRepository()

    val retrieveSucces: LiveData<Boolean> = quizRepository.retrieveSucces
    val quiz: LiveData<ArrayList<Quiz>> = quizRepository.quiz
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getQuiz() {
        viewModelScope.launch {
            try {
                quizRepository.getQuiz()
            } catch (ex: QuizRepository.QuizRetrievalError){
                val errorMsh = "Something went wrong while retrieving quiz"
                Log.e(TAG, ex.message ?: errorMsh)
                _errorText.value = errorMsh
            }
        }
    }
}