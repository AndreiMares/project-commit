package com.example.andre.verifypresency

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.viewmodel.RegisterViewModel
import com.example.andre.verifypresency.source.remote.register.RegisterRepository
import com.example.andre.verifypresency.util.InjectorUtils

class ViewModelFactory(private val registerRepository: RegisterRepository)
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
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
                            InjectorUtils.provideRegisterRepository())
                            .also { INSTANCE = it }
                }


    }
}