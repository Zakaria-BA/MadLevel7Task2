package com.example.madlevel7task2.rrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel7task2.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.withTimeout

class QuizRepository {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var quizVragen =
        firestore.collection("quiz_vragen")

    private val _quiz: MutableLiveData<ArrayList<Quiz>> = MutableLiveData()

    val quiz: LiveData<ArrayList<Quiz>>
        get() = _quiz

    suspend fun getQuiz() {
        try {
            //firestore has support for coroutines via the extra dependency we've added :)
            withTimeout(5_000) {

            }
        }  catch (e : Exception) {
            throw QuizRetrievalError("Retrieval-firebase-task was unsuccessful")
        }
    }

    class QuizRetrievalError(message: String) : Exception(message)

}