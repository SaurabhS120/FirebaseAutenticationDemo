package com.example.firebaseautenticationdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth




class SplashActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        val fireBaseViewModel:FireBaseViewModel by viewModels()
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            if (fireBaseViewModel.isLoggedIn()) goToLoggedInActivity()
            else goToLoginActivity()
        },1000)
    }
    private fun goToLoginActivity(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    private fun goToLoggedInActivity(){
        val intent = Intent(this,LoggedInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}