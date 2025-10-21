package com.example.getuserwithretrofitmvi.di

import org.koin.dsl.module

val appModule = module {
    
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }

}
