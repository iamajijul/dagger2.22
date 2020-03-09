package com.scansione.mitchdagger222.network.main

import com.scansione.mitchdagger222.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getUserPosts(@Query("userId") userID: String): Flowable<List<Post>>
}