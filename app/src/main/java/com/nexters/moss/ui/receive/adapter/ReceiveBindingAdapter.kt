package com.nexters.moss.ui.receive.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener

class ReceiveBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("cakeColor")
        fun cakeColor(view:RelativeLayout, color : Int){
            view.setBackgroundResource(color)
        }


        @JvmStatic
        @BindingAdapter("showGif")
        fun showGif(layout: RelativeLayout, imagePath: String) {
            val imageView = ImageView(layout.context)

            Glide.with(layout)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .load("http:/" + imagePath)
                //.load(R.raw.green)
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
                            registerAnimationCallback(object :
                                Animatable2Compat.AnimationCallback() {
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

            layout.addView(imageView)
        }
    }
}
