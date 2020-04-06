package com.nexters.moss.utils

import com.kakao.auth.ISessionCallback
import com.kakao.util.exception.KakaoException

class KakaoSessionCallback : ISessionCallback {
    private var onSessionOpenedListener: OnSessionOpenedListener? = null

    override fun onSessionOpenFailed(exception: KakaoException?) {
        exception?.printStackTrace()
    }

    override fun onSessionOpened() {
        onSessionOpenedListener?.onOpened()
        DLog.d("hello")
    }

    fun setOnSessionOpenedCallback(listener: () -> Unit) {
        onSessionOpenedListener = object : OnSessionOpenedListener {
            override fun onOpened() {
                listener()
            }
        }
    }

    interface OnSessionOpenedListener {
        fun onOpened()
    }
}