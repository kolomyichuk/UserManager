package com.example.getuserwithretrofitmvi.di

import com.example.getuserwithretrofitmvi.data.repository.UserRepository
import org.koin.dsl.module

val appModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideUserApi(get()) }
    single { UserRepository(get()) }
}