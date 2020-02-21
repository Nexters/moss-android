package com.nexters.moss.ui.diary_history

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogDiaryHistoryBinding
import com.nexters.moss.model.HistoryModel
import com.nexters.moss.ui.diary_history.adapter.DiaryHistoryAdapter
import com.nexters.moss.utils.DLog
import kotlinx.android.synthetic.main.dialog_diary_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryHistoryDialog : BaseDialog<DialogDiaryHistoryBinding>() {

    override val vm: DiaryHistoryViewModel by viewModel()

    var item: HistoryModel? = null

    override fun getLayoutRes() = R.layout.dialog_diary_history
    override fun setupBinding() {
        binding.vm = vm
    }

    lateinit var habikeryToken: String

    override fun getDialogWidth() = 370
    override fun getDialogHeight() = 560

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        item = arguments?.getParcelable(KEY)

        setChange()
        setRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {
            exit.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                }
            })
        }
    }

    private fun setRecyclerView() {

        DLog.d("" + item!!.dates)

        val recyclerAdapter = DiaryHistoryAdapter(item!!.dates)
        val recyclerManager = LinearLayoutManager(context!!)

        layout_history_recycler_view.apply {
            adapter = recyclerAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }

    private fun setChange() {
        vm.changeCakeImage(item!!.imagePath)
        //showGif()

        DLog.d(item!!.imagePath)
        vm.setTextHabit(item!!.habitName)
        vm.setTextDescription(item!!.description + " " + item!!.cakeName)
    }

    private fun showGif() {
        val linearLayout = view!!.findViewById<LinearLayout>(R.id.layout_cake_gif)
        val imageView = ImageView(context)
        Glide.with(this)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .load("http:/"+item!!.imagePath)
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
        const val KEY = "key"
    }
}
