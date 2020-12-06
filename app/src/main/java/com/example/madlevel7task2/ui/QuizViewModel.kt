package com.example.madlevel7task2.ui

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel7task2.model.Quiz
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val _quiz: MutableLiveData<List<Quiz>> = MutableLiveData()

    val quiz = _quiz

    fun getQuiz() {
        viewModelScope.launch {
            FirebaseDatabase.getInstance().reference.child("quiz")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val vragen = ArrayList<Quiz>()
                        for (currentQuestion: DataSnapshot in snapshot.children) {
                            val antwoorden = listOf<String>(
                                currentQuestion.child("antwoorden")
                                    .child("answer_1").value.toString(),
                                currentQuestion.child("antwoorden")
                                    .child("answer_2").value.toString(),
                                currentQuestion.child("antwoorden")
                                    .child("answer_3").value.toString()
                            )

                            val quiz = Quiz(
                                currentQuestion.child("vraag").value.toString(),
                                antwoorden,
                                currentQuestion.child("goeie_antwoord").value.toString()
                            )
                            vragen.add(quiz)
                        }
                        _quiz.value = vragen
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(ContentValues.TAG, error.toString())
                    }
                })
        }
    }
}