package com.neppplus.colosseum_20211024.datas

import org.json.JSONObject
import java.io.Serializable

class UserData : Serializable {

    var id = 0 // Int가 들어올거라는 명시.
    var email = "" // String이 들어올거라는 명시.
    var nickname = ""

    companion object {

        fun getUserDataFromJson( jsonObj: JSONObject ) : UserData {

            val userData = UserData()

            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickname = jsonObj.getString("nick_name")

            return userData
        }

    }


}