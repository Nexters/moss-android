package com.nexters.moss.utils

import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.coroutines.delay

object KakaoLoginUtils {
    const val EMPTY = "empty"

    suspend fun getLoginId(): String {
        var id = EMPTY

//        var isClosed = false

        UserManagement.getInstance().me(object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                id = result?.id.toString()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                errorResult?.exception?.printStackTrace()
//                isClosed = true
            }
        })

        val timeout = 200
        var currentTime = 0

        while (id == EMPTY) {
            delay(10)
            currentTime += 10

            if (currentTime == timeout) {
                DLog.e("timeout get token")
                break
            }

//            if (isClosed) {
//                DLog.e("session closed")
//                break
//            }
        }

        DLog.i("id value is : $id")
        return id
    }

    suspend fun unlink(): String {
        var result = EMPTY

        var isFail = false

        UserManagement.getInstance().requestUnlink(object : UnLinkResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                errorResult?.exception?.printStackTrace()
                let {
                    result = "onFailure"
                    isFail = true
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult) {
                errorResult.exception?.printStackTrace()
                let {
                    result = "onSessionClosed"
                    isFail = true
                }
            }

            override fun onNotSignedUp() {
                let {
                    result = "onNotSignedUp"
                    isFail = true
                }
            }

            override fun onSuccess(userId: Long?) {
                result = userId?.toString() ?: "onSuccess"
            }
        })

        val timeout = 500
        var currentTime = 0

        while (result == EMPTY) {
            delay(10)
            currentTime += 10
            if (currentTime == timeout) {
                result = "timeout"
                break
            }

            if (isFail) {
                DLog.e(result)
                break
            }
        }

        return result
    }
}