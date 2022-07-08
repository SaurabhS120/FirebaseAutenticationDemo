package com.example.firebaseautenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.firebaseautenticationdemo.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateAccountBinding
    lateinit var fireBaseViewModel: FireBaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fireBaseViewModel:FireBaseViewModel by viewModels()
        val viewModel:CreateAccountViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.createAccountButton.setOnClickListener {
            if (viewModel.isvalid()){
                fireBaseViewModel.createUserWithEmailAndPassword(viewModel.getUserNameText(), viewModel.getPasswordText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Firebase", "createUserWithEmail:success")
                            onBackPressed()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Utils.showToast(this,it.message.toString())
                    }
            }
        }

    }
    fun goToLoggedInActivity() {
        val intent = Intent(this, LoggedInActivity::class.java)
        startActivity(intent)
    }
}