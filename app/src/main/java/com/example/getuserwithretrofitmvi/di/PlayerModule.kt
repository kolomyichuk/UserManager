package com.example.getuserwithretrofitmvi.di

import androidx.media3.exoplayer.ExoPlayer
import com.example.getuserwithretrofitmvi.ui.screens.player.PlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single { ExoPlayer.Builder(get()).build() }
    viewModel { PlayerViewModel(get()) }
}
