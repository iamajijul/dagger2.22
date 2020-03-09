package com.scansione.mitchdagger222.di.auth

import com.scansione.mitchdagger222.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
@Module
class AuthModule {
    @AuthScope
    @Provides
    fun provideAuthApi(retrofit : Retrofit) : AuthApi?{
        return retrofit.create(AuthApi::class.java)
    }
}