package com.scansione.mitchdagger222

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.scansione.mitchdagger222.ui.auth.AuhActivity
import com.scansione.mitchdagger222.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    val TAG = "Base Activity"
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToObserver()
    }

    private fun subscribeToObserver(){
        sessionManager.authUser?.observe(this, androidx.lifecycle.Observer {
            if(it!=null){

                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED ->{
                        Log.d(TAG,"Login Successfully for user ${it.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Log.d(TAG,"Login UnSuccessfully")
                    }
                    AuthResource.AuthStatus.LOADING -> {}
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        })
    }

    fun navLoginScreen(){
        startActivity(Intent(this,AuhActivity::class.java))
    }

}