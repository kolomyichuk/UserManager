package com.example.getuserwithretrofitmvi.di

import com.example.getuserwithretrofitmvi.data.repository.UserRepository
import com.example.getuserwithretrofitmvi.ui.screens.users.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideUserApi(get()) }
    single { UserRepository(get()) }
    viewModel { UserViewModel(get()) }
}
