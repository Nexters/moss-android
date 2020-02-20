package com.nexters.moss.ui.diary_history.adapter

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DiaryHistoryBindingAdapter {
//    companion object {
//        @JvmStatic
//        @BindingAdapter("changeCakeImage")
//        fun changeCakeImage(image: LinearLayout, src: String) {
//            Glide.with(image.context).asFile().load(src).into(image)
//
//
//            val linearLayout = view!!.findViewById<LinearLayout>(R.id.layout_cake_gif)
//            val imageView = ImageView(context)
//            Glide.with(this)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .load(item!!.imagePath)
//                .listener(object : RequestListener<GifDrawable> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: com.bumptech.glide.request.target.Target<GifDrawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: GifDrawable?,
//                        model: Any?,
//                        target: com.bumptech.glide.request.target.Target<GifDrawable>?,
//                        dataSource: com.bumptech.glide.load.DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        resource?.apply {
//                            registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
//                                override fun onAnimationEnd(drawable: Drawable?) {
//                                    super.onAnimationEnd(drawable)
//                                }
//
//                                override fun onAnimationStart(drawable: Drawable?) {
//                                    super.onAnimationStart(drawable)
//                                }
//                            })
//                            setLoopCount(1)
//                        }
//
//                        return false
//                    }
//                })
//                .into(imageView)
//
//            linearLayout.addView(imageView)
//
//        }
//
//    }
}