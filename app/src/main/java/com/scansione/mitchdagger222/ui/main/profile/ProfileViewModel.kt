package com.scansione.mitchdagger222.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.scansione.mitchdagger222.SessionManager
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager) : ViewModel() {

    val TAG = "ProfileViewModel"

    init {

        Log.d(TAG, "Profile View Model Injected")
    }

    fun getAuthenticateUser(): LiveData<AuthResource<User?>>? {
        return sessionManager.authUser
    }
}