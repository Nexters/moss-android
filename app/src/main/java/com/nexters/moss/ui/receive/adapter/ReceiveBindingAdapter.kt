package com.nexters.moss.ui.receive.adapter

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.nexters.moss.R

class ReceiveBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("showGif")
        fun showGif(layout: ViewGroup, imagePath: String) {
            var imageView = ImageView(layout.context)
//
//            val lp = ViewGroup.LayoutParams(50, 50)
//
//            imageView.marginStart
//
//            imageView.layoutParams = lp
//            var imageView: ImageView

            for (item in layout.children.iterator()) {
                if (item.id == R.id.gif) {
                    imageView = item as ImageView
                }
            }

            Glide.with(layout.context)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .load("http:/" + imagePath)
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
//            layout.addView(imageView)
        }
    }
}
