package com.neppplus.colosseum_20211024.datas

class ReplyData(var id: Int, var content: String) {

//    var user : UserData  댓글을 적은 사람 정보 => 하위 데이터로.
    
    constructor() : this(0, "내용없음")
    
}