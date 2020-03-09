package com.scansione.mitchdagger222.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scansione.mitchdagger222.R
import com.scansione.mitchdagger222.models.User
import com.scansione.mitchdagger222.ui.auth.AuthResource
import com.scansione.mitchdagger222.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    lateinit var profileViewModel: ProfileViewModel

    val TAG = "Profile Fragment"
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
        subscribeToObserver()

    }

    fun subscribeToObserver(){
        profileViewModel.getAuthenticateUser()?.removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticateUser()?.observe(viewLifecycleOwner, Observer {
            if(it!=null){

                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED ->{
                        Log.d(TAG,"Login Successfully for user ${it.data?.email}")
                        setUserdata(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Log.d(TAG,"Login UnSuccessfully")
                        setErrorData(it.message)
                    }
                }
            }
        })
    }

    private fun setErrorData(message: String?) {
        email.text = message
        username.text = message
        website.text = message

    }

    private fun setUserdata(data: User?) {
        email.text = data?.email
        username.text = data?.name
        website.text = data?.website

    }
}