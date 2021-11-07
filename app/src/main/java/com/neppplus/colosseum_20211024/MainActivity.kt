package com.neppplus.colosseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_20211024.databinding.ActivityMainBinding
import com.neppplus.colosseum_20211024.datas.TopicData
import com.neppplus.colosseum_20211024.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        /v2/main_info API가 토론 주제 목록을 내려줌.
//        서버 호출 => 파싱해서, mTopicList를 채워주자.
        getTopicListFromServer()


    }

    fun getTopicListFromServer() {

        ServerUtil.getRequestMainInfo(mContext,  object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {



            }

        })

    }


}