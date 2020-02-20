package com.nexters.moss.di

import com.nexters.moss.ui.dialog_add_habit.AddHabitViewModel
import com.nexters.moss.ui.dialog_first_gift.FirstGiftViewModel
import com.nexters.moss.ui.dialog_logout.LogoutViewModel
import com.nexters.moss.ui.dialog_receive_cake.ReceiveCakeViewModel
import com.nexters.moss.ui.dialog_remove_habit.RemoveHabitViewModel
import com.nexters.moss.ui.dialog_withdraw.WithdrawViewModel
import com.nexters.moss.ui.formation_habit.FormationHabitViewModel
import com.nexters.moss.ui.diary.DiaryPieceViewModel
import com.nexters.moss.ui.diary.DiaryViewModel
import com.nexters.moss.ui.diary.DiaryWholeViewModel
import com.nexters.moss.ui.diary_history.DiaryHistoryViewModel
import com.nexters.moss.ui.main.MainViewModel
import com.nexters.moss.ui.receive.ReceiveViewModel
import com.nexters.moss.ui.make_nickname.MakeNicknameViewModel
import com.nexters.moss.ui.dialog_report.ReceiveDialogViewModel
import com.nexters.moss.ui.send.SendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MakeNicknameViewModel(get()) }

    viewModel { MainViewModel(get(), get()) }
    viewModel { FormationHabitViewModel(get()) }

    viewModel { SendViewModel(get(), get()) }
    viewModel { ReceiveViewModel() }
    viewModel { ReceiveDialogViewModel() }

    viewModel { DiaryViewModel() }
    viewModel { DiaryPieceViewModel() }
    viewModel { DiaryWholeViewModel() }
    viewModel { DiaryHistoryViewModel() }

    viewModel { WithdrawViewModel(get()) }
    viewModel { LogoutViewModel() }
    viewModel { FirstGiftViewModel() }

    viewModel { AddHabitViewModel() }
    viewModel { ReceiveCakeViewModel() }
    viewModel { RemoveHabitViewModel(get()) }
}