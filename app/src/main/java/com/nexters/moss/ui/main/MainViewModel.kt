package com.nexters.moss.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.HabitModel
import com.nexters.moss.model.NewCakeModel
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.utils.CategoryState
import com.nexters.moss.utils.DLog
import com.nexters.moss.utils.DateHelper
import com.nexters.moss.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class MainViewModel(
    private val userRepo: UserRepository,
    private val habitRepo: HabitRepository
) : ViewModel() {
    private val _isOpenDrawer = MutableLiveData<Boolean>().apply { value = false }
    val isOpenDrawer: LiveData<Boolean> get() = _isOpenDrawer

    private val _isWithDraw = MutableLiveData<Boolean>(false)
    val isWithDraw: LiveData<Boolean> get() = _isWithDraw

    private val _isLogout = MutableLiveData<Boolean>(false)
    val isLogout: LiveData<Boolean> get() = _isLogout

    private val _isEditMode = MutableLiveData<Boolean>(false)
    val isEditMode: LiveData<Boolean> get() = _isEditMode

    private val _intentDiary = MutableLiveData<Boolean>(false)
    val intentDiary: LiveData<Boolean> get() = _intentDiary

    private val _intentSend = MutableLiveData<Boolean>(false)
    val intentSend: LiveData<Boolean> get() = _intentSend

    private val _nickname = MutableLiveData<String>("미확인")
    val nickname: LiveData<String> get() = _nickname

    private val _itemList = MutableLiveData<ArrayList<HabitModel>>(ArrayList())
    val itemList: LiveData<ArrayList<HabitModel>> get() = _itemList

    private val _dayOfFirst = MutableLiveData<String>()
    val dayOfFirst: LiveData<String> get() = _dayOfFirst

    private val _dayOfSecond = MutableLiveData<String>()
    val dayOfSecond: LiveData<String> get() = _dayOfSecond

    private val _dayOfThird = MutableLiveData<String>()
    val dayOfThird: LiveData<String> get() = _dayOfThird

    private val _dayOfFourth = MutableLiveData<String>()
    val dayOfFourth: LiveData<String> get() = _dayOfFourth

    private val _dayOfFifth = MutableLiveData<String>()
    val dayOfFifth: LiveData<String> get() = _dayOfFifth

    private val _receivedCake = MutableLiveData<NewCakeModel>()
    val receivedCake: LiveData<NewCakeModel> get() = _receivedCake

    private val _isFirstCheck = MutableLiveData<Int>()
    val isFirstCheck: LiveData<Int> get() = _isFirstCheck

    val firstCheckEvent = SingleLiveEvent<Int>()


    fun openDrawer() {
        DLog.d("open drawer")
        _isOpenDrawer.value = true
    }

    fun setDrawerState(isOpened: Boolean) {
        _isOpenDrawer.value = isOpened
    }

    fun withDraw() {
        setDrawerState(false)
        _isWithDraw.value = true
        _isWithDraw.value = false
    }

    fun logout() {
        setDrawerState(false)
        _isLogout.value = true
        _isLogout.value = false
    }

    fun setEditMode(enabled: Boolean) {
        DLog.d("call $enabled")
        _isEditMode.value = enabled
    }

    fun openDiary() {
        _intentDiary.value = true
    }

    fun openSend() {
        _intentSend.value = true
    }

    fun setNickname(habikeryToken: String) {
        viewModelScope.launch {
            val response = userRepo.getUserInfo(habikeryToken)
            val name = response.nickname ?: "unknown"

//            val nameBuilder = StringBuilder()
//            for (i in 1 until name.length - 1) {
//                nameBuilder.append(name[i])
//            }
//
//            _nickname.value = nameBuilder.toString()
            _nickname.value = name
        }
    }

    fun refreshItemList(habikeryToken: String) {
        viewModelScope.launch {
            val response = habitRepo.getHabit(habikeryToken).data as ArrayList
            _itemList.value = response


            CategoryState.resetCategoryState()
            for (habit in response) {
                CategoryState.setCategoryState(habit.categoryId - 1, true)
            }
        }
    }

    fun doneHabit(habikeryToken: String, habitId: Int) {
        viewModelScope.launch {
            val response = habitRepo.doneHabit(habikeryToken, habitId)

            val newModel = response.habitModel
            val receivedCake = response.newCakeDTO

            val list = _itemList.value ?: ArrayList()

            for (i in list.indices) {
                if (newModel.categoryId == list[i].categoryId)
                    list[i] = newModel
            }

            _itemList.value = list


            if (newModel.firstCheck) {
                firstCheckEvent.call(newModel.categoryId)
            }

            receivedCake?.let {
                _receivedCake.value = it
            }
        }
    }

    fun refreshDate() {
        val days = DateHelper.getFiveDays()

        _dayOfFirst.value = days[0]
        _dayOfSecond.value = days[1]
        _dayOfThird.value = days[2]
        _dayOfFourth.value = days[3]
        _dayOfFifth.value = days[4]
    }

    fun changeOrderHabit(habikeryToken: String, habitId: Int, order: Int) {
        viewModelScope.launch {
            val response = habitRepo.changeOrderHabit(habikeryToken, habitId, order)
            DLog.d(response.toString())
        }
    }
}
