package com.neppplus.colosseum_20211024.datas

import org.json.JSONObject

class ReplyData(
    var id: Int,
    var content: String) {

    var likeCount = 0
    var dislikeCount = 0
    var myLike = false
    var myDislike = false
    var replyCount = 0


//    이 댓글을 적은 사람? UserData 클래스 활용
    var user = UserData()

//    이 댓글이 선택한 진영? SideData 클래스 활용
    var selectedSide = SideData()
    
    constructor() : this(0, "내용없음")

    companion object {

//        jsonObj -> ReplyData로 변환해주는 기능

        fun getReplyDataFromJson( jsonObj : JSONObject ) : ReplyData {

            val  replyData = ReplyData()

            replyData.id = jsonObj.getInt("id")
            replyData.content = jsonObj.getString("content")

            replyData.likeCount = jsonObj.getInt("like_count")
            replyData.dislikeCount = jsonObj.getInt("dislike_count")
            replyData.myLike = jsonObj.getBoolean("my_like")
            replyData.myDislike = jsonObj.getBoolean("my_dislike")
            replyData.replyCount = jsonObj.getInt("reply_count")

//            선택한 진영도 파싱
            val selectedSideObj = jsonObj.getJSONObject("selected_side")

//            진영 정보를 파싱하기에 적당한 JSONObject 추출 -> SideData의 변환기능에 넣어보자.
            replyData.selectedSide =  SideData.getSideDataFromJSON(selectedSideObj)


//            적은 사람도 파싱
            val userObj = jsonObj.getJSONObject("user")
            replyData.user = UserData.getUserDataFromJson(userObj)



            return replyData

        }

    }

}