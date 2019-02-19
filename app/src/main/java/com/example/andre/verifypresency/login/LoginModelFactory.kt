package com.example.andre.verifypresency.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.login.remote.LoginRepository
import com.example.andre.verifypresency.util.InjectorUtils

class LoginModelFactory(private val loginRepository: LoginRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(loginRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T


    companion object {

        @Volatile
        private var INSTANCE: LoginModelFactory? = null

        fun getInstance(): LoginModelFactory =
                INSTANCE
                        ?: synchronized(LoginModelFactory::class.java) {
                            INSTANCE
                                    ?: LoginModelFactory(
                                            InjectorUtils.provideLoginRepository())
                                            .also { INSTANCE = it }
                        }


    }
}