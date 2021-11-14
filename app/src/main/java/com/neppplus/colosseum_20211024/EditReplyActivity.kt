package com.neppplus.colosseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_20211024.databinding.ActivityEditReplyBinding
import com.neppplus.colosseum_20211024.datas.TopicData
import com.neppplus.colosseum_20211024.utils.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

    lateinit var binding: ActivityEditReplyBinding

    lateinit var mTopicData : TopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_reply)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        binding.writeReplyBtn.setOnClickListener {

            val inputContent = binding.replyEdt.text.toString()

            ServerUtil.postRequestWriteReply(mContext, mTopicData.id, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {



                }

            })

        }

    }

    override fun setValues() {

        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.topicTitleTxt.text = mTopicData.title

        binding.selectedSideTitleTxt.text = mTopicData.mySide!!.title


    }

}