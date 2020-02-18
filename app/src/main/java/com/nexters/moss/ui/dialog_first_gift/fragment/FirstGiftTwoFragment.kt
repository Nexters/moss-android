package com.nexters.moss.ui.dialog_first_gift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.ui.dialog_first_gift.adapter.CakeListAdapter

class FirstGiftTwoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_first_gift_two, container, false)

        val rv = v.findViewById<RecyclerView>(R.id.rv_cakeListAll)
        rv.adapter = CakeListAdapter()
        rv.layoutManager = GridLayoutManager(context, 3)

        return v
    }
}