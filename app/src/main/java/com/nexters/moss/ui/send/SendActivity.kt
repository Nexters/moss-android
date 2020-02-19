package com.nexters.moss.ui.send

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivitySendBinding
import com.nexters.moss.model.CakeModel
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.send.adapter.SendAdapter
import com.nexters.moss.ui.send.adapter.SendListDecoration
import com.nexters.moss.ui.send.keyboard.KeyboardEventVisibility
import kotlinx.android.synthetic.main.activity_send.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendActivity : BaseActivity<ActivitySendBinding>() {
    override val vm: SendViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_send
    override fun setupBinding() {
        binding.vm = vm
    }

    private lateinit var keyboardVisibility: KeyboardEventVisibility

    private var cakeList = arrayListOf<CakeModel>(
        CakeModel("물마시기", R.drawable.send_select_watermelon),
        CakeModel("스트레칭", R.drawable.send_select_cheese),
        CakeModel("명상", R.drawable.send_select_cream),
        CakeModel("산책", R.drawable.send_select_green_tea),
        CakeModel("뉴스보기", R.drawable.send_select_coffee),
        CakeModel("아침먹기", R.drawable.send_select_apple),
        CakeModel("일기쓰기", R.drawable.send_select_chestnut),
        CakeModel("책읽기", R.drawable.send_select_almond)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setCakeList(cakeList)

        setupCakeRecyclerView()
        observeViewModel()
        textChangeListener()
        detectKeyBoard()
    }

    private fun observeViewModel() {
        with(vm) {

            exit.observe(this@SendActivity, Observer {
                if (it) {
                    finish()
                }
            })

            main.observe(this@SendActivity, Observer {
                if (it) {
                    startActivity<MainActivity>()
                    showToastReportCompleted()
                }
            })
        }
    }

    private fun setupCakeRecyclerView() {

        layout_send_cake_recycler.apply {
            adapter = SendAdapter().apply {
                setOnFirstItemListener {
                    changeItems(R.drawable.send_watermelon,"물마시는", "수박")
                }

                setOnItemClickListener { position ->
                    //toast(" " + position)
                    when (position) {
                        0 -> {
                            changeItems(R.drawable.send_watermelon, "물마시는", "수박")
                        }
                        1 -> {
                            changeItems(R.drawable.send_cheese, "스트레칭하는","치즈")
                        }
                        2 -> {
                            changeItems(R.drawable.send_cream, "명상하는", "생크림")
                        }
                        3 -> {
                            changeItems(R.drawable.send_green_tea, "산책하는", "녹차")
                        }
                        4 -> {
                            changeItems(R.drawable.send_coffee, "뉴스보는", "커피")
                        }
                        5 -> {
                            changeItems(R.drawable.send_apple, "아침먹는", "사과")
                        }
                        6 -> {
                            changeItems(R.drawable.send_chestnut, "일기쓰는", "밤")
                        }
                        7 -> {
                            changeItems(R.drawable.send_almond, "책을읽는", "아몬드")
                        }
                    }
                }
            }

            layoutManager = LinearLayoutManager(
                this@SendActivity, RecyclerView.HORIZONTAL, false
            )

            addItemDecoration(SendListDecoration())
        }
    }

    private fun changeItems(img : Int, toYou :String, cakeName:String){
        vm.apply{
            changeCakeImage(img)
            changeString(toYou)
            changeCakeName(cakeName)
        }
    }

    private fun textChangeListener() {
        edit_txt_send_cake_message.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.length > 2) {
                    vm.isTextLengthEnough(true)
                } else {
                    vm.isTextLengthEnough(false)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun detectKeyBoard() {
        keyboardVisibility = KeyboardEventVisibility(window,
            onShowKeyboard = { keyboardHeight ->
                layout_send_scroll.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }

                vm.isBtnVisible(false)
            })

        keyboardVisibility = KeyboardEventVisibility(window,
            onHideKeyboard = {
                vm.isBtnVisible(true)
            })
    }

    private fun showToastReportCompleted() {

        val toastTopValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            21f,
            this.resources.displayMetrics
        ).toInt()


        Toast(this).apply {
            view = layoutInflater.inflate(R.layout.layout_toast_complete, null)
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, toastTopValue)
            show()
        }
    }
}
