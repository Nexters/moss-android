package com.nexters.moss.di

import com.nexters.moss.utils.KakaoSessionCallback
import org.koin.dsl.module

val utilsModule = module {
    factory { KakaoSessionCallback() }
}