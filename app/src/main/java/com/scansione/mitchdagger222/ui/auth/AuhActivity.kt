package com.scansione.mitchdagger222.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.scansione.mitchdagger222.R
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.ui.main.MainActivity
import com.scansione.mitchdagger222.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuhActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var user : User
    val LOG = "AuthActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeToObserver()
        login_button.setOnClickListener(this)
//        Log.e(LOG,user)
    }

    private fun subscribeToObserver() {
        authViewModel.getUser()?.observe(this, Observer {

            if (it != null) {

                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        handleProgress(false)
                        Log.d(LOG, "Login Successfully for user ${it.data?.email}")
                        navToMainActivity()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        handleProgress(false)
                        Log.d(LOG, "Login UnSuccessfully")
                        Toast.makeText(this, "Login UnSuccessful", Toast.LENGTH_LONG).show()
                    }
                    AuthResource.AuthStatus.LOADING -> handleProgress(true)
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> handleProgress(false)
                }
            }
        })

    }

    private fun navToMainActivity() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun handleProgress(isShow: Boolean) {
        if (isShow) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.login_button -> {
                doAuthentication()
            }
        }
    }

    private fun doAuthentication() {
        if (TextUtils.isEmpty(user_id_input.text.toString()))
            return
        authViewModel.authenticateUser(user_id_input.text.toString().toInt())
    }
}
