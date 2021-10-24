package com.neppplus.colosseum_20211024.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

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

//            만들어진 request를 실제로 호출 해야함.
//            서버에 요청을 실제로 하자. -> 클라이언트의 역할. -> 앱이 클라이언트로써 동작하게 하자.

            val client = OkHttpClient()


//            OkHttpClient를 이용 -> 서버에 로그인 기능 호출
//            호출을 했으면 -> 서버가 알려준 결과를 받아서 처리. (response 처리)

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    실패 : 서버 연결 자체를 실패. 아무 응답도 없을때.
//                    ex. 인터넷 끊김, 서버 접속 불가 등등 물리적 연결 실패.
//                    비번 틀려서 로그인 실패 : 연결은 되었고, 응답도 잘 돌아왔는데 -> 그 내용만 실패. (응답 O)
                }

                override fun onResponse(call: Call, response: Response) {

//                    어떤 내용이든, 응답 자체가 잘 들어온 경우. (로그인 성공, 실패 모두 응답 O)
//                    응답에 포함된 데이터 들 중 => 본문 (body) 을 보자.

                    val bodyString = response.body!!.string()

//                    본문을 그냥 받은 String 그대로 찍으면 -> 한글이 깨져서 보임.
//                    해결책 : String -> JSONObject로 변환 -> String으로 재변환해보면, 한글이 제대로 보임.

                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답본문", jsonObj.toString())


//                    연습. code 숫자 추출. 로그인 성공 여부 판단 -> 로그로 출력
//                    "code" 숫자 -> 제일 큰 중괄호 (jsonObj) 에 바로 달려있음. -> jsonObj에게 찾아달라고 하자.
                    val codeNum = jsonObj.getInt("code")
                    Log.d("로그인코드값", codeNum.toString())

//                    연습. 로그인에 성공했을때만, 성공한 사람의 닉네임을 로그로 출력.

                    if (codeNum == 200) {

//                        data 이름표가 붙은 {  }를 추출하자. => 그 내부를 파고들 수 있다.
                        val dataObj = jsonObj.getJSONObject("data")
//                        user { } 추출. -> 그 내부의 닉네임 추출하자.
                        val userObj = dataObj.getJSONObject("user")

                        val nickname = userObj.getString("nick_name")

                        Log.d("로그인한사람", nickname)

                    }

                }

            })

        }

    }

}