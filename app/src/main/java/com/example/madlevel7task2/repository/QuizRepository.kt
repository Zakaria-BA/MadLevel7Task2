package com.example.madlevel7task2.repository

import com.example.madlevel7task2.model.Quiz
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class QuizRepository {

    private var database: DatabaseReference = Firebase.database.reference
    private var quizListener: ValueEventListener? = null

//    suspend fun getQuiz(){
//        try {
//            quizListener = object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val quiz = snapshot.getValue<Quiz>()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            }
//        } catch (e: Exception){
//
//        }
//    }
}