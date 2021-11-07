package com.neppplus.colosseum_20211024.datas

import org.json.JSONObject

class SideData {

    var id = 0  // Int가 들어올것이라고 명시
    var title = "" // String이 들어올 자리라고 명시
    var voteCount = 0

    companion object {

        fun getSideDataFromJSON( jsonObj: JSONObject ) : SideData {
            val sideData = SideData()

            sideData.id = jsonObj.getInt("id")
            sideData.title = jsonObj.getString("title")
            sideData.voteCount = jsonObj.getInt("vote_count")

            return sideData

        }

    }

}