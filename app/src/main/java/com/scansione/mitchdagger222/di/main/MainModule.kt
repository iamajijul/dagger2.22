package com.scansione.mitchdagger222.di.main

import com.scansione.mitchdagger222.network.main.MainApi
import com.scansione.mitchdagger222.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    @MainScope
    @Provides
    fun providePostAdapter ():PostsRecyclerAdapter{
        return PostsRecyclerAdapter()
    }

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}