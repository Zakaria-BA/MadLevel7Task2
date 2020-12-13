package com.example.madlevel7task2.rrepository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel7task2.model.Quiz
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import kotlin.math.log

class QuizRepository {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var quizVragen =
            firestore.collection("quiz_vragen")

    private val _quiz: MutableLiveData<ArrayList<Quiz>> = MutableLiveData()

    val quiz: LiveData<ArrayList<Quiz>>
        get() = _quiz

    private val _retrieveSucces: MutableLiveData<Boolean> = MutableLiveData()

    val retrieveSucces: LiveData<Boolean>
        get() = _retrieveSucces

    suspend fun getQuiz() {
        val quizQuestions: ArrayList<Quiz> = ArrayList()
        try {
            withTimeout(5_000) {
                val data = quizVragen
                        .get()
                        .await()

                for (currentItem: DocumentSnapshot in data.documents) {
                    val antwoorden = listOf(currentItem.getString("antwoord_1").toString(), currentItem.getString("antwoord_2").toString(), currentItem.getString("antwoord_3").toString())

                    val currentQuiz = Quiz(currentItem.getString("vraag").toString(), antwoorden, currentItem.getString("goeie_antwoord").toString())
                    quizQuestions.add(currentQuiz)
                    Log.d(TAG, currentQuiz.toString())
                    Log.d(TAG, quizQuestions.toString())
                }
                _retrieveSucces.value = true
                _quiz.value = quizQuestions
            }
        } catch (e: Exception) {
            throw e
        }
    }

    class QuizRetrievalError(message: String) : Exception(message)

}