package com.scansione.mitchdagger222.di

import com.scansione.mitchdagger222.di.auth.AuthModule
import com.scansione.mitchdagger222.di.auth.AuthScope
import com.scansione.mitchdagger222.di.auth.AuthViewModelsModule
import com.scansione.mitchdagger222.di.main.MainFragmentBuildersModule
import com.scansione.mitchdagger222.di.main.MainModule
import com.scansione.mitchdagger222.di.main.MainScope
import com.scansione.mitchdagger222.di.main.MainViewModelsModule
import com.scansione.mitchdagger222.ui.auth.AuhActivity
import com.scansione.mitchdagger222.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun connectAuthActivity(): AuhActivity


    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelsModule::class, MainModule::class]
    )
    abstract fun connectMainActivity(): MainActivity

}