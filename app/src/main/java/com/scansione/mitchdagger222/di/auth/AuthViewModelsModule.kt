package com.scansione.mitchdagger222.di.auth

import androidx.lifecycle.ViewModel
import com.scansione.mitchdagger222.di.ViewModelKey
import com.scansione.mitchdagger222.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel

}