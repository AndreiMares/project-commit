package com.example.andre.verifypresency

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.login.LoginViewModel
import com.example.andre.verifypresency.register.RegisterViewModel
import com.example.andre.verifypresency.source.remote.user.UserRepository
import com.example.andre.verifypresency.util.InjectorUtils

class ViewModelFactory(private val userRepository: UserRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(RegisterViewModel::class.java) ->
                        RegisterViewModel(userRepository)

                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(/*userRepository*/)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T


    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
                            InjectorUtils.provideUserRepository())
                            .also { INSTANCE = it }
                }


    }
}