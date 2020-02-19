package com.nexters.moss.ui.diary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.ui.diary.DiaryWholeFragment

class DiaryPieceRecyclerAdapter : RecyclerView.Adapter<DiaryPieceRecyclerAdapter.Holder>() {
    private var context : Context? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtClear = itemView.findViewById<TextView>(R.id.txt_diary_item_clear)
        private val txtSubjective = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_subjective)
        private val txtCakeName = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_name)
        private val imgCount = itemView.findViewById<ImageView>(R.id.img_diary_item_cake_count)
        private val txtCount = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_count)

        fun bind(item : DiaryCakeModel) {
            txtClear?.text = item.habitClear
            txtSubjective?.text = item.cakeSubjective
            txtCakeName?.text = item.cakeName

            imgCount?.visibility = View.GONE
            txtCount?.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_diary, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cakeList[position])
    }


    override fun getItemCount(): Int {
        return cakeList.size
    }

//    fun setCakeList(list : ArrayList<DiaryCakeModel>){
//        cakeList = list
//        notifyDataSetChanged()
 //   }
}