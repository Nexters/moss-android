package com.nexters.moss._base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    abstract val vm: ViewModel

    protected val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as T
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun setupBinding()
    @ColorRes
    abstract fun getStatusBarColor(): Int

    private fun setStatusBarColor(@ColorRes id: Int) {
        window.statusBarColor = ContextCompat.getColor(this, id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(getStatusBarColor())
        setupBinding()
        binding.lifecycleOwner = this
    }

    inline fun <reified I : Activity> startActivity() {
        startActivity(Intent(applicationContext, I::class.java))
    }

    inline fun <reified I : Activity> startActivityForResult(requestCode: Int) {
        startActivityForResult(Intent(applicationContext, I::class.java), requestCode)
    }

    fun toast(content: String) {
        Toast.makeText(applicationContext, content, Toast.LENGTH_SHORT).show()
    }
}