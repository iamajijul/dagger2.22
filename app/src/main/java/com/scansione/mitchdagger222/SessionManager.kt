package com.scansione.mitchdagger222

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.ui.auth.AuthResource
import com.scansione.mitchdagger222.ui.auth.AuthResource.Companion.loading
import com.scansione.mitchdagger222.ui.auth.AuthResource.Companion.logout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    // data
    private val cachedUser: MediatorLiveData<AuthResource<User?>>? =
        MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User?>>?) {
        if (cachedUser != null) {
            cachedUser.value = loading(
                null as User?
            )
            cachedUser.addSource<AuthResource<User?>>(
                source!!,
                Observer<AuthResource<User?>> { userAuthResource ->
                    cachedUser.value = userAuthResource
                    cachedUser.removeSource(
                        source
                    )
                    if (userAuthResource.status == AuthResource.AuthStatus.ERROR) {
                        cachedUser.value = logout()
                    }
                })
        }
    }

    fun logOut() {
        Log.d(
            TAG,
            "logOut: logging out..."
        )
        cachedUser!!.value = logout()
    }

    val authUser: LiveData<AuthResource<User?>>?
        get() = cachedUser

    companion object {
        private const val TAG = "DaggerExample"
    }
}