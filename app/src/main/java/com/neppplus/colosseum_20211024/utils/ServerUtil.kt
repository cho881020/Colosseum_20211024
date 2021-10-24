package com.neppplus.colosseum_20211024.utils

import okhttp3.FormBody
import okhttp3.Request

class ServerUtil {

//    static 에 대응되는 기능 활용

    companion object {

//        어느 서버로 가는가? BASE_URL을 미리 변수에 담아두자.
        val BASE_URL = "http://54.180.52.26"


//        이 {  } 안에 적는 코드들은 다른 클래스에서 ServerUtil.변수/기능 활용 가능.

        fun postRequestLogin( email: String, pw: String ) {

//            1. 어디로 요청하러 (인터넷 주소 연결 - URL) 갈것인가?
            val urlString = "${BASE_URL}/user"

//            2. 파라미터를 어떻게 들고 갈것인가? - POST : formData 활용
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

//            3. 최종 Request 정보 완성 -> 어떤 방식으로 갈지도 같이 명시.
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

        }

    }

}