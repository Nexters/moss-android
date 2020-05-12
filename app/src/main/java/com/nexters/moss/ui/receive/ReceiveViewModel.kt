package com.nexters.moss.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss.model.NewCakeModel

class ReceiveViewModel : ViewModel() {

    private val _report = MutableLiveData<Boolean>().apply{ value = false}
    val report : LiveData<Boolean> get() = _report

    private val _exit = MutableLiveData<Boolean>().apply{ value = false }
    val exit : LiveData<Boolean> get() = _exit

    private val _diary = MutableLiveData<Boolean>().apply{ value = false }
    val diary : LiveData<Boolean> get() = _diary

    private val _send = MutableLiveData<Boolean>().apply{ value = false }
    val send : LiveData<Boolean> get() = _send

    private val _nickname = MutableLiveData<String>("채채")
    val nickname: LiveData<String> get() = _nickname

    private val _note = MutableLiveData<String>("화이팅!!")
    val note: LiveData<String> get() = _note
    
    private val _description = MutableLiveData<String>("산책하는 나에게")
    val description : LiveData<String> get() = _description

    private val _cakeName = MutableLiveData<String>("녹차케익")
    val cakeName: LiveData<String> get() = _cakeName

    private val _imagePath = MutableLiveData<String>("")
    val imagePath: LiveData<String> get() = _imagePath

    private val _backColor = MutableLiveData<Int>(GREEN)
    val backColor : LiveData<Int> get() = _backColor

    fun setNewCake(newCake: NewCakeModel) {
        newCake.let {
            _nickname.value = it.nickname
            _note.value = it.note
            _cakeName.value = it.cakeName
            _imagePath.value = it.imagePath
            _description.value = it.description
        }

        _backColor.value = when(_cakeName.value){
            "수박","사과" -> CORAL
            "밤", "생크림" -> BLUE
            "치즈", "책읽기" -> ORANGE
            "커피" -> BROWN
            else -> GREEN
        }
   }

    fun clickBtnReport() {
        _report.value = true
    }

    fun exit(){
        _exit.value = true
    }

    fun goDiary(){
        _diary.value = true
    }

    fun goSend(){
        _send.value = true
    }

    companion object {
        const val CORAL = R.color.coral
        const val ORANGE = R.color.orange
        const val GREEN = R.color.green
        const val BLUE = R.color.blue
        const val BROWN = R.color.brown
    }
}
