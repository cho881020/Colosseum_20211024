package com.neppplus.colosseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.colosseum_20211024.adapters.ReplyAdapter
import com.neppplus.colosseum_20211024.databinding.ActivityViewTopicDetailBinding
import com.neppplus.colosseum_20211024.datas.ReplyData
import com.neppplus.colosseum_20211024.datas.TopicData
import com.neppplus.colosseum_20211024.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding:  ActivityViewTopicDetailBinding

    lateinit var mTopicData : TopicData

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter: ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.voteToFirstSideBtn.setOnClickListener {

//            첫번째 진영에 투표 => 새로 투표 현황 받아서 -> UI 반영

        }

    }

    override fun setValues() {
        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.topicTitleTxt.text = mTopicData.title

        Glide.with(mContext).load(mTopicData.imageURL).into( binding.topicImg )

        binding.replyCountTxt.text = "현재 의견 : ${mTopicData.replyCount}개"

        binding.firstSideTitleTxt.text = mTopicData.sideList[0].title
        binding.secondSideTitleTxt.text = mTopicData.sideList[1].title

        binding.firstSideVoteCountTxt.text = "${mTopicData.sideList[0].voteCount}표"
        binding.secondSideVoteCountTxt.text = "${mTopicData.sideList[1].voteCount}표"

        getTopicDetailFromServer()


        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item,  mReplyList)
        binding.replyListView.adapter = mReplyAdapter

    }

    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, "NEW", object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

//                댓글 목록 JSONArrary -> 파싱 -> mReplyList의 자료로 추가

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                topicObj (JSONObject)를 새 TopicData로 파싱 => 최신정보 반영

                mTopicData = TopicData.getTopicDataFromJSON(topicObj)

                val repliesArr = topicObj.getJSONArray("replies")

                for ( i   in   0 until  repliesArr.length()  ) {

                    val replyObj = repliesArr.getJSONObject(i)

//                    JSONObject -> ReplyData 객체로 변환.
                    val replyData = ReplyData()
                    replyData.id =  replyObj.getInt("id")
                    replyData.content = replyObj.getString("content")

//                    댓글목록으로 추가
                    mReplyList.add( replyData )

                }

//                리스트뷰의 목록에 변경 => 어댑터 새로고침 ( UI 변경 )

                runOnUiThread {
                    mReplyAdapter.notifyDataSetChanged()
                }



            }

        })

    }


}