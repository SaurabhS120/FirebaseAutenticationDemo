package com.example.firebaseautenticationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firebaseautenticationdemo.databinding.ActivityLoggedInBinding

class LoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_logged_in)
        val firebaseViewModel:FireBaseViewModel by viewModels()
        binding.userName.setText(firebaseViewModel.userName())
    }
}