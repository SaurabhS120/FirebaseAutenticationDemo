package com.example.firebaseautenticationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticationdemo.databinding.ActivityLoggedInBinding

class LoggedInActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoggedInBinding
    lateinit var firebaseViewModel:FireBaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val firebaseViewModel:FireBaseViewModel by viewModels()
        this.firebaseViewModel = firebaseViewModel
        Log.d("state","Logged in activity")
        binding.userName.setText(firebaseViewModel.userName())
        binding.logoutButton.setOnClickListener {
            firebaseViewModel.logout()
            startActivity(Intent(this,LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }
}