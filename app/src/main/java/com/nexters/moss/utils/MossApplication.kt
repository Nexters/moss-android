package com.nexters.moss.utils

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.kakao.auth.*
import com.nexters.moss.di.networkModule
import com.nexters.moss.di.repositoryModule
import com.nexters.moss.di.utilsModule
import com.nexters.moss.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MossApplication : Application() {
    companion object { var debugMode: Boolean = true }

    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false

        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = (0 != ((appInfo.flags) and ApplicationInfo.FLAG_DEBUGGABLE))
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return debuggable
    }

    override fun onCreate() {
        super.onCreate()
        debugMode = isDebuggable(this)

        startKoin {
            androidContext(this@MossApplication)
            modules(viewModelModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(utilsModule)
        }

        initKakaoOauth(applicationContext)
    }

    private fun initKakaoOauth(context: Context) {
        KakaoSDK.init(object : KakaoAdapter() {
            override fun getSessionConfig(): ISessionConfig {
                return object : ISessionConfig {
                    override fun getAuthTypes(): Array<AuthType> {
                        return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                    }

                    override fun isUsingWebviewTimer(): Boolean {
                        return false
                    }

                    override fun isSecureMode(): Boolean {
                        return false
                    }

                    override fun getApprovalType(): ApprovalType? {
                        return ApprovalType.INDIVIDUAL
                    }

                    override fun isSaveFormData(): Boolean {
                        return true
                    }
                }
            }

            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig { context }
            }
        })
    }

}