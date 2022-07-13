package com.example.firebaseautenticationdemo

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class FireBaseViewModel : ViewModel(){
    val TAG = "firebase"
    val mAuth:FirebaseAuth
    val mFirebaseRemoteConfig:FirebaseRemoteConfig
    init {
        mAuth = FirebaseAuth.getInstance()
        mFirebaseRemoteConfig = initRemoteConfig()
    }
    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }
    fun userName(): String{
        var name = mAuth.currentUser?.displayName?:""
        if (name.isEmpty()) name = mAuth.currentUser?.email?:"no-name-found"
        return name

    }
    fun createUserWithEmailAndPassword(email:String,password:String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }
    fun signInWithEmailAndPassword(email:String,password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email,password)
    }
    fun logout(){
        mAuth.signOut()
    }

    fun initRemoteConfig(): FirebaseRemoteConfig {
        var mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(5)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        return mFirebaseRemoteConfig
    }
}