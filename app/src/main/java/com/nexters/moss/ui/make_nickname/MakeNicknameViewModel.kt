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

    fun makeNickname() {
        viewModelScope.launch {
            try {
                val response = userRepo.join(
                    "z9Q8qWwlE2PbDDZ4QZ5qKj72WZmE4u82I__Uxwo9dZsAAAFwU1f1PQ",
                    nickname.value ?: "unknown"
                )
                DLog.d(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            _intentMain.value = true
        }
    }
}