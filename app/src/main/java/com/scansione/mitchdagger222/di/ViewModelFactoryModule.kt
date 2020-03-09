package com.scansione.mitchdagger222.di

import androidx.lifecycle.ViewModelProvider
import com.scansione.mitchdagger222.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class  ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}