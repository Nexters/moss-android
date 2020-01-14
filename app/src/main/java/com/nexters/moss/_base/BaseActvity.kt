package com.nexters.moss._base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<T: ViewDataBinding, V: ViewModel>(c: Class<V>) : AppCompatActivity() {
    protected val viewModel by lazy {
        ViewModelProviders.of(this).get(c)
    }

    protected val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as T
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun setupBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        binding.lifecycleOwner = this
    }

    inline fun<reified I : Activity> startActivity() {
        startActivity(Intent(applicationContext, I::class.java))
    }

    inline fun<reified I : Activity> startActivityForResult(requestCode: Int) {
        startActivityForResult(Intent(applicationContext, I::class.java), requestCode)
    }

    fun toast(content: String) {
        Toast.makeText(applicationContext, content, Toast.LENGTH_SHORT).show()
    }
}