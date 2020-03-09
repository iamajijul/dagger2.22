package com.scansione.mitchdagger222.ui.main.post

import android.util.Log
import androidx.lifecycle.*
import com.scansione.mitchdagger222.SessionManager
import com.scansione.mitchdagger222.models.Post
import com.scansione.mitchdagger222.network.main.MainApi
import com.scansione.mitchdagger222.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi) :
    ViewModel() {

    private var posts: MediatorLiveData<Resource<List<Post>?>>? = null
    val TAG = "Post ViewModel"


    init {
        Log.d(TAG, "Post ViewModel")
    }

    fun observePost(): LiveData<Resource<List<Post>?>>? {

        if (posts == null) {
            posts = MediatorLiveData()
            posts?.value = Resource.loading(null as List<Post>?)
            val userId = sessionManager.authUser?.value?.data?.id.toString()
            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getUserPosts(userId)
                    .onErrorReturn(object : Function<Throwable, List<Post>> {
                        override fun apply(t: Throwable?): List<Post> {
                            Log.e(TAG, "Apply :$t")
                            val post = Post(userId, -1, "", "")
                            post.id = -1
                            val failedPosts = ArrayList<Post>()
                            failedPosts.add(post)
                            return failedPosts
                        }

                    })
                    .map(object : Function<List<Post>, Resource<List<Post>?>?> {
                        override fun apply(it: List<Post>?): Resource<List<Post>?>? {
                            if (it?.isNotEmpty() == true) {
                                if (it?.get(0)?.id == -1) {
                                    return Resource.error(
                                        "Something went worng",
                                        null as List<Post>?
                                    )
                                }
                            }

                            return Resource.success(it)
                        }

                    })
                    .subscribeOn(Schedulers.io())
            )
            posts?.addSource(
                source
            ) {
                posts?.value = it
                posts?.removeSource(source)
            }

        }

        return posts
    }


}