package com.nexters.moss.ui.make_nickname

import android.os.Bundle
import androidx.lifecycle.Observer
import com.kakao.auth.Session
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.ActivityMakeNicknameBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.utils.DLog
import com.nexters.moss.utils.KakaoLoginUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakeNicknameActivity : BaseActivity<ActivityMakeNicknameBinding>() {
    override val vm: MakeNicknameViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_make_nickname
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        vm.setAccessToken(intent.getStringExtra(EXTRA_KAKAO_ID) ?: throw Exception("need intent extra"))
        vm.setAccessToken(Session.getCurrentSession().tokenInfo.accessToken ?: throw Exception("need intent extra"))

        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
//        GlobalScope.launch {
//            KakaoLoginUtils.unlink()
//        }
//        DLog.d("종료됨")
    }

    private fun observeViewModel() {
        with(vm) {
            intentMain.observe(this@MakeNicknameActivity, Observer {
                if (it) {
                    setTokenInSharedPreference()
                    startActivity<MainActivity>()
                    finish()
                }
            })
        }
    }

    private fun setTokenInSharedPreference() {
        val sp = getUserSharedPreference()
        sp.edit().run {
            putString(
                SharedPreferenceConstant.ACCESS_TOKEN.getValue(),
                vm.getAccessToken()
            )
            putString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                vm.getHabikeryToken()
            )
        }.apply()
    }

    companion object {
        const val EXTRA_KAKAO_ID = "extra_kakao_id"
    }
}
