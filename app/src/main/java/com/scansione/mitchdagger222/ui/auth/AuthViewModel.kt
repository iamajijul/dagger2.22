package com.scansione.mitchdagger222.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.scansione.mitchdagger222.SessionManager
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.network.auth.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(var authApi: AuthApi?,var sessionManager: SessionManager) : ViewModel() {

    val LOG = "AuthViewModel"
    init {

        Log.d("aaa", "ViewModel Injecting is Working")


    }

    fun authenticateUser(id: Int) {
        Log.d(LOG, "attemptLogin: attempting to login.")
        sessionManager.authenticateWithId(queryUserId(id))

    }

    private fun queryUserId(id: Int): LiveData<AuthResource<User?>>? {

        return LiveDataReactiveStreams
                .fromPublisher(authApi?.getUser(id)?.
                    onErrorReturn(object :  Function<Throwable, User> {
                        override fun apply(t: Throwable?): User {
                            val errorUser = User(-1,"","","")
                            return errorUser
                        }

                    })?.
                    map(object : Function<User,AuthResource<User?>>{

                        override fun apply(t: User?): AuthResource<User?> {
                            if(t?.id == -1){
                                return AuthResource.error("Could not Authenticated",null as User?)
                            }
                            return AuthResource.authenticated(t)
                        }

                    })?.
                    subscribeOn(Schedulers.io())!!)
    }

    fun getUser(): LiveData<AuthResource<User?>>? {
        return sessionManager.authUser
    }
}