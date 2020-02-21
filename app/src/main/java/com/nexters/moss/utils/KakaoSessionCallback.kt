package com.nexters.moss.utils

import com.kakao.auth.ISessionCallback
import com.kakao.util.exception.KakaoException
import kotlinx.coroutines.delay

class KakaoSessionCallback : ISessionCallback {
    var opening = false
    var isFail = false
    override fun onSessionOpenFailed(exception: KakaoException?) {
        exception?.printStackTrace()
//        isFail = true
    }

    override fun onSessionOpened() {
        opening = true
    }

    suspend fun isOpening(): Boolean {
        val timeout = 5000
        var currentTime = 0
        while (!opening) {
            delay(10)
            currentTime += 10
//            if (isFail) {
//                DLog.e("onSessionOpenFailed")
//                return false
//            }

            if (currentTime == timeout) {
                DLog.e("open fail timeout")
                return false
            }
        }
        return true
    }
}