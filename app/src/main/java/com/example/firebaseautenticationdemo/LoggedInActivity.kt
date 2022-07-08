package com.example.firebaseautenticationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
    }
}