package com.nexters.moss.ui.dialog_logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.utils.KakaoLoginUtils
import com.nexters.moss.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LogoutViewModel : ViewModel() {
    val loginSuccessEvent = SingleLiveEvent<Unit>()

    fun logout() {
        viewModelScope.launch {
            var isSuccess = KakaoLoginUtils.logout()

            if (isSuccess) {
                loginSuccessEvent.call(Unit)
            }
        }
    }
}