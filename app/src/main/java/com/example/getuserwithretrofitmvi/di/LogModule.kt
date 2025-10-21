package com.example.getuserwithretrofitmvi.di

import com.example.getuserwithretrofitmvi.data.local.LogFileDataSource
import com.example.getuserwithretrofitmvi.data.repository.LogRepository
import com.example.getuserwithretrofitmvi.data.worker.LogWorker
import com.example.getuserwithretrofitmvi.ui.screens.log.LogViewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val logModule = module {

    single { LogFileDataSource(get()) }
    single { LogRepository(get()) }
    viewModel { LogViewModel(get()) }
    worker { LogWorker(get(), get(), get()) }

}
