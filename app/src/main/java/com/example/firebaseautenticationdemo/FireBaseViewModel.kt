package com.example.firebaseautenticationdemo

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class FireBaseViewModel : ViewModel(){
    lateinit var mAuth:FirebaseAuth
    fun onCreate(){
        mAuth = FirebaseAuth.getInstance()
    }
    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }
    fun userName(): String{
        return mAuth.currentUser?.displayName?:"no-name"
    }
}