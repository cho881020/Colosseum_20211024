package com.neppplus.colosseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_20211024.databinding.ActivityViewReplyDetailBinding
import com.neppplus.colosseum_20211024.datas.ReplyData
import com.neppplus.colosseum_20211024.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var binding: ActivityViewReplyDetailBinding

    lateinit var mReplyData : ReplyData

    val mReReplyList = ArrayList<ReplyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mReplyData = intent.getSerializableExtra("reply") as ReplyData

        binding.writerNicknameTxt.text = mReplyData.user.nickname
        binding.selectedSideTitleTxt.text = mReplyData.selectedSide.title
        binding.contentTxt.text = mReplyData.content

        getReplyDetailFromServer()

    }

    fun getReplyDetailFromServer() {

        ServerUtil.getRequestReplyDetail(mContext, mReplyData.id, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

            }

        })

    }

}