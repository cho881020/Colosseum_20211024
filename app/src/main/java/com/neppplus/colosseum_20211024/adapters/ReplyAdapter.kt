package com.neppplus.colosseum_20211024.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.colosseum_20211024.R
import com.neppplus.colosseum_20211024.datas.ReplyData
import com.neppplus.colosseum_20211024.datas.TopicData

class ReplyAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<ReplyData>) : ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = mInflater.inflate(R.layout.reply_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]


        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)
        val likeCountTxt = row.findViewById<TextView>(R.id.likeCountTxt)
        val dislikeCountTxt = row.findViewById<TextView>(R.id.dislikeCountTxt)

        replyCountTxt.text = "답글 : ${data.replyCount}개"
        
        likeCountTxt.text = "좋아요 : ${data.likeCount}개"
        dislikeCountTxt.text = "싫어요 : ${data.dislikeCount}개"


        contentTxt.text = data.content



        return row
    }

}