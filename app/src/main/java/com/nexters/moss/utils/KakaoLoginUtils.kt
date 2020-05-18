package com.nexters.moss.utils

import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.coroutines.delay
import com.kakao.usermgmt.callback.LogoutResponseCallback



object KakaoLoginUtils {
    const val EMPTY = "empty"

    suspend fun getLoginId(): String {
        var id = EMPTY

        UserManagement.getInstance().me(object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                id = result?.id.toString()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                errorResult?.exception?.printStackTrace()
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
        }

        DLog.i("id value is : $id")
        return id
    }

    suspend fun getAccessToken(): String {
        var token = EMPTY

        AuthService.getInstance().requestAccessTokenInfo(object : ApiResponseCallback<AccessTokenInfoResponse>() {
            override fun onSuccess(result: AccessTokenInfoResponse?) {
                token = result?.userId?.toString() ?: EMPTY

            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                errorResult?.exception?.printStackTrace()
            }
        })
//        val timeout = 200
//        var currentTime = 0

        while (token == EMPTY) {
            delay(10)
//            currentTime += 10

//            if (currentTime == timeout) {
//                DLog.e("timeout get token")
//                break
//            }
        }

        DLog.i("token value is : $token")

        return token
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

    suspend fun logout(): Boolean {
        var isComplete = false

        UserManagement.getInstance()
            .requestLogout(object : LogoutResponseCallback() {
                override fun onCompleteLogout() {
                    isComplete = true
                }
            })

        var currentTime = 0

        while (!isComplete || currentTime < 2000) {
            delay(100)
            currentTime += 100
        }

        return isComplete
    }
}