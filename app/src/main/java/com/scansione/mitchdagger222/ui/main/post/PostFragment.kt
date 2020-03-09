package com.scansione.mitchdagger222.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scansione.mitchdagger222.R
import com.scansione.mitchdagger222.ui.main.Resource
import com.scansione.mitchdagger222.utils.VerticalSpacingItemDecoration
import com.scansione.mitchdagger222.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    lateinit var postViewModel : PostViewModel
    val TAG = "POST FRAGMENT"

    @Inject
    lateinit var adapter: PostsRecyclerAdapter
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        postViewModel = ViewModelProvider(this,viewModelProviderFactory).get(PostViewModel::class.java)
        subscribeObserver()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.addItemDecoration(VerticalSpacingItemDecoration(12))
        recycler_view.adapter = adapter
    }

    private fun subscribeObserver(){
        postViewModel.observePost()?.removeObservers(viewLifecycleOwner)
        postViewModel.observePost()?.observe(viewLifecycleOwner, Observer {

            if(it!=null){
                when(it.status){
                    Resource.Status.SUCCESS -> {
                    adapter.setPosts(it.data?:ArrayList())
                    }
                    Resource.Status.ERROR -> {

                    }
                    Resource.Status.LOADING -> {

                    }
                }
            }
        })
    }
}