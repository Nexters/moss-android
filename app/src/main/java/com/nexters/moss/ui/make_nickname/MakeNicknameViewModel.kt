package com.nexters.moss.ui.make_nickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MakeNicknameViewModel : ViewModel() {
    val nickname = MutableLiveData<String>("")
}