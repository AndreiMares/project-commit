package com.example.andre.verifypresency.register

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.util.InjectorUtils

class RegisterModelFactory(private val registerRepository: RegisterRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(RegisterViewModel::class.java) ->
                        RegisterViewModel(registerRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T


    companion object {

        @Volatile
        private var INSTANCE: RegisterModelFactory? = null

        fun getInstance(): RegisterModelFactory =
                INSTANCE
                        ?: synchronized(RegisterModelFactory::class.java) {
                            INSTANCE
                                    ?: RegisterModelFactory(
                                            InjectorUtils.provideRegisterRepository())
                                            .also { INSTANCE = it }
                        }


    }
}