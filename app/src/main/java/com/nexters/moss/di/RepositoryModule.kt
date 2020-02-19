package com.nexters.moss.di

import com.nexters.moss.repository.CakeRepository
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HabitRepository(get()) }
    single { UserRepository(get()) }
    single { CakeRepository(get()) }
}