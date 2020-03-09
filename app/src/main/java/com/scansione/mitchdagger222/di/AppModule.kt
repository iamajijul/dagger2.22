package com.scansione.mitchdagger222.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.scansione.mitchdagger222.R
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideRequestOption(): RequestOptions {
            return RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideGlideInstance(
            application: Application,
            requestOptions: RequestOptions
        ): RequestManager {
            return Glide.with(application).applyDefaultRequestOptions(requestOptions)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideAppDrawable(application: Application): Drawable {
            return ContextCompat.getDrawable(application, R.drawable.logo)!!
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideUser(): User {
            return User(0,"","","")
        }
    }

}