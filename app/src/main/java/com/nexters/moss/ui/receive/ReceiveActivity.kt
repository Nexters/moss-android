package com.nexters.moss.ui.receive

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityReceiveBinding
import com.nexters.moss.ui.dialog_report.ReceiveDialog
import com.nexters.moss.ui.diary.DiaryActivity
import com.nexters.moss.ui.send.SendActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {
    override val vm: ReceiveViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_receive
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        showGif()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_SEND_CAKE -> {
                if (resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }

            REQUEST_CAKE_DIARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }
        }
    }

    private fun observeViewModel() {
        with(vm) {
            report.observe(this@ReceiveActivity, Observer {
                if (it) {
                    ReceiveDialog().show(supportFragmentManager, "")
                }
            })

            exit.observe(this@ReceiveActivity, Observer {
                if (it) {
                    finish()
                }
            })

            diary.observe(this@ReceiveActivity, Observer {
                if (it) {
//                    startActivity<DiaryActivity>()
                    startActivityForResult(
                        Intent(applicationContext, DiaryActivity::class.java),
                        REQUEST_CAKE_DIARY
                    )
                }
            })

            send.observe(this@ReceiveActivity, Observer {
                if (it) {
                    startActivityForResult(
                        Intent(
                            applicationContext,
                            SendActivity::class.java
                        ).apply {
                            putExtra(SendActivity.COME_FROM, SendActivity.FROM_RECEIVE_CAKE)
                        }, REQUEST_SEND_CAKE
                    )
                }
            })
        }
    }

    private fun showGif() {
        val linearLayout = findViewById<RelativeLayout>(R.id.layout_cake)
        val imageView = ImageView(this)
        Glide.with(this)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .load(R.raw.green)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.apply {
                        registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                            override fun onAnimationEnd(drawable: Drawable?) {
                                super.onAnimationEnd(drawable)
                            }

                            override fun onAnimationStart(drawable: Drawable?) {
                                super.onAnimationStart(drawable)
                            }
                        })
                        setLoopCount(1)
                    }

                    return false
                }
            })
            .into(imageView)

        linearLayout.addView(imageView)


    }

    companion object {
        const val REQUEST_SEND_CAKE = 1000
        const val REQUEST_CAKE_DIARY = 2000

        const val EXTRA_NEW_CAKE_MODEL = "extra_new_cake_model"
    }
}



