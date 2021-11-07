package com.neppplus.colosseum_20211024.datas

import org.json.JSONObject
import java.io.Serializable

class TopicData (
    var id: Int,
    var title: String,
    var imageURL: String) : Serializable  {

//    의견 (댓글) 갯수 변수
    var replyCount = 0

//    선택 진영 "목록"
    val sideList = ArrayList<SideData>()


//    토픽데이터 만들때는 빈 괄호 TopicData() 형태도 지원하자.
//    다른 형태의 생성자 (보조 생성자) 도 추가 지원.

    constructor() : this(0, "제목없음", "주소없음")

    companion object {

//        매번 파싱 코드를 화면마다 짜기가 번거롭다.
//        TopicData의 기능으로 -> 토론에대한 내용을 갖고있는 jsonObj를 넣으면
//          => 파싱을 진행해서 -> TopicData 형태로 변환해주는 기능.

//        다른 화면에서는 jsonObj만 파싱해서 => 변환 기능으로 활용만.

        fun getTopicDataFromJSON( jsonObj : JSONObject ) : TopicData {

            val topicData = TopicData()

            topicData.id = jsonObj.getInt("id")
            topicData.title = jsonObj.getString("title")
            topicData.imageURL = jsonObj.getString("img_url")

            topicData.replyCount = jsonObj.getInt("reply_count")

            return topicData

        }


    }


}