package com.example.firebaseautenticationdemo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.google.firebase.auth.FirebaseAuth
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class SplashActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        val fireBaseViewModel: FireBaseViewModel by viewModels()
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FacebookSdk.setClientToken(applicationContext.getString(R.string.facebook_client_token))
        FacebookSdk.sdkInitialize(applicationContext)
        if (BuildConfig.DEBUG) {
            try {
                val info = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                )
                for (signature in info.signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
                }
            } catch (e: PackageManager.NameNotFoundException) {
            } catch (e: NoSuchAlgorithmException) {
            }
        }
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