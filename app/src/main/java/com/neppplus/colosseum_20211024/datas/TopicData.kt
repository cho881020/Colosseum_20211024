package com.neppplus.colosseum_20211024.datas

import java.io.Serializable

class TopicData (
    var id: Int,
    var title: String,
    var imageURL: String) : Serializable  {

//    토픽데이터 만들때는 빈 괄호 TopicData() 형태도 지원하자.
//    다른 형태의 생성자 (보조 생성자) 도 추가 지원.

    constructor() : this(0, "제목없음", "주소없음")

}