package com.example.firebaseautenticationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticationdemo.databinding.ActivityLoggedInBinding
import com.google.android.gms.tasks.OnCompleteListener


class LoggedInActivity : AppCompatActivity() {
    val TAG = "firebase"
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
        firebaseViewModel.mFirebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener(this, OnCompleteListener<Boolean?> { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Utils.showToast(this,"Fetch and activate succeeded")
                } else {
                    Utils.showToast(this,"Fetch failed")
                }

                binding.messageTextView.setText(firebaseViewModel.mFirebaseRemoteConfig.getString("msg"))
                setSeason(firebaseViewModel.mFirebaseRemoteConfig.getString("season"))
            })
        binding.messageTextView.setText(firebaseViewModel.mFirebaseRemoteConfig.getString("msg"))
    }
    fun setSeason(seasonName:String){
        Utils.showToast(this,"Season : $seasonName")
        Seasons.values().forEach {
            if (it.seasonName.equals(seasonName)){
                binding.greetImageView.setImageResource(it.imageResId)
            }
        }
    }
}