package com.nexters.moss.ui.make_nickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch
import java.lang.Exception

class MakeNicknameViewModel(private val userRepo: UserRepository) : ViewModel() {
    val nickname = MutableLiveData<String>("")

    private val _intentMain = MutableLiveData<Boolean>(false)
    val intentMain: LiveData<Boolean> get() = _intentMain

    private var accessToken = ""
    private var habikeryToken = ""

    fun makeNickname() {
        viewModelScope.launch {
            try {
                val response = userRepo.join(
                    accessToken,
                    nickname.value ?: "unknown"
                )
                DLog.d(response.toString())

                login()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            val response = userRepo.login(accessToken)
            habikeryToken = response.habikeryToken ?: "unknownToken"
            DLog.d("hello")

            _intentMain.value = true
        }
    }

    fun setAccessToken(token: String) {
        accessToken = token
    }

    fun getAccessToken() = accessToken
    fun getHabikeryToken() = habikeryToken
}