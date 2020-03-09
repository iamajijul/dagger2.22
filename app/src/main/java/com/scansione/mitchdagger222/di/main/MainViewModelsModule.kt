package com.scansione.mitchdagger222.di.main

import androidx.lifecycle.ViewModel
import com.scansione.mitchdagger222.di.ViewModelKey
import com.scansione.mitchdagger222.ui.main.post.PostViewModel
import com.scansione.mitchdagger222.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel):ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(postViewModel: PostViewModel):ViewModel
}