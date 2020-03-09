package com.scansione.mitchdagger222.di.main

import com.scansione.mitchdagger222.ui.main.post.PostFragment
import com.scansione.mitchdagger222.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun connectProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun connectPostFragment(): PostFragment
}