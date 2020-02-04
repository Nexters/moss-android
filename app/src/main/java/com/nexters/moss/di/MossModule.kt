package com.nexters.moss.di

import com.nexters.moss.ui.diary.DiaryPieceViewModel
import com.nexters.moss.ui.diary.DiaryViewModel
import com.nexters.moss.ui.diary.DiaryWholeViewModel
import com.nexters.moss.ui.main.MainViewModel
import com.nexters.moss.ui.make_nickname.MakeNicknameViewModel
import com.nexters.moss.ui.send.SendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// 매개변수로 get() 을 사용하면 선언한 클래스중 알맞은 클래스를 알아서 넣어준다.
val appModule = module {
    // single<C> { 생성자 } 싱글톤 객체 생성
    // factory<C> { 생성자 } 일반 객체 생성
}

val viewModelModule = module {
    viewModel { MakeNicknameViewModel() }
    viewModel { SendViewModel() }
    viewModel { MainViewModel() }
    viewModel { DiaryViewModel() }
    viewModel { DiaryPieceViewModel() }
    viewModel { DiaryWholeViewModel() }
}