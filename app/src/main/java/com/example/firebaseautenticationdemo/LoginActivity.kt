package com.example.firebaseautenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.TaskStackBuilder
import com.example.firebaseautenticationdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fireBaseViewModel:FireBaseViewModel by viewModels()
        val viewModel:LoginViewModel by viewModels()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginButton.setOnClickListener {
            if(viewModel.isDataValid()){
                fireBaseViewModel.signInWithEmailAndPassword(viewModel.getEmailText(),viewModel.getPasswordText())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Utils.showToast(baseContext,"Login successful")
                            Log.d("firebase","Login successful")
                            goToLoggedInActivity()
                        }else{
                            Utils.showToast(baseContext,"Login fail")
                            Log.d("firebase","Login fail")
                        }
                    }
                    .addOnFailureListener {
                        Utils.showToast(this,it.message.toString())
                    }

            }
        }

        binding.createAccountButton.setOnClickListener {
            goToCreateAccountPage()
        }

    }

    fun goToCreateAccountPage(){
        val intent = Intent(this,CreateAccountActivity::class.java)
        startActivity(intent)
    }

    fun goToLoggedInActivity(){
        val intent = Intent(this,LoggedInActivity::class.java)
        startActivity(intent)
    }
}