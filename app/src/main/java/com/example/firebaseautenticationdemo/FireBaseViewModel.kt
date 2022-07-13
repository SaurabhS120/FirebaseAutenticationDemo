package com.example.firebaseautenticationdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FireBaseViewModel : ViewModel(){
    val TAG = "firebase"
    lateinit var mAuth:FirebaseAuth
    init {
        mAuth = FirebaseAuth.getInstance()
    }
    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }
    fun userName(): String{
        var name = mAuth.currentUser?.displayName?:""
        if (name.isEmpty()) name = mAuth.currentUser?.email?:"no-name-found"
        return name

    }
    fun createUserWithEmailAndPassword(email:String,password:String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }
    fun signInWithEmailAndPassword(email:String,password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email,password)
    }
    fun logout(){
        mAuth.signOut()
    }
}