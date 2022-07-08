package com.example.firebaseautenticationdemo

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAccountViewModel : ViewModel(){

    enum class UserNameErrors(val s:String?){

        None(null),
        EmptyUserName("Please enter username");

        fun getMessage(): String? =s?.toString()
    }
    enum class PasswordErrors(val s:String?){

        None(null),
        EmptyUserName("Please enter password");

        fun getMessage(): String? =s?.toString()
    }

    val userName = MutableLiveData("")
    val password = MutableLiveData("")
    val userNameError = MutableLiveData(UserNameErrors.None)
    val passwordError = MutableLiveData(PasswordErrors.None)

    fun isvalid():Boolean {
        var error = false
        if (userName.value.isNullOrEmpty()){
            userNameError.postValue(UserNameErrors.EmptyUserName)
            error = true
        }
        if (password.value.isNullOrEmpty()){
            passwordError.postValue(PasswordErrors.EmptyUserName)
            error = true
        }
        return error.not()
    }

    fun getUserNameText(): String {
        return userName.value?:""
    }
    fun getPasswordText(): String {
        return password.value?:""
    }


}