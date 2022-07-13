package com.example.firebaseautenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.TaskStackBuilder
import com.example.firebaseautenticationdemo.databinding.ActivityLoginBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth

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

        binding.createAccountWithOtherMethodsButton.setOnClickListener {
            signIn()
        }

    }

    fun goToCreateAccountPage(){
        val intent = Intent(this,CreateAccountActivity::class.java)
        startActivity(intent)
    }

    fun goToLoggedInActivity(){
        val intent = Intent(this,LoggedInActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    fun signIn(){
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            .setAndroidPackageName( /* yourPackageName= */
                "com.example.firebaseautenticationdemo",  /* installIfNotAvailable= */
                true,  /* minimumVersion= */
                null)
            .setHandleCodeInApp(true) // This must be set to true
            .setUrl("https://google.com") // This URL needs to be whitelisted
            .build()

        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder()
                .setActionCodeSettings(actionCodeSettings)
                .build()
        )
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            Utils.showToast(this,"Firebase login success")
            goToLoggedInActivity()
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            Utils.showToast(this,"Firebase login failed")
        }
    }
}