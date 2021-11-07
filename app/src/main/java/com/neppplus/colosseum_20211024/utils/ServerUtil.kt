package com.neppplus.colosseum_20211024.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    돌아온 응답을 화면에 전달 : 나(ServerUtil)에게 발생한 일을 => 화면단에서 대신 처리해달라고 하자. (interface 활용)
    interface JsonResponseHandler {
        fun onResponse( jsonObj: JSONObject )
    }

//    static 에 대응되는 기능 활용

    companion object {

//        어느 서버로 가는가? BASE_URL을 미리 변수에 담아두자.
        val BASE_URL = "http://54.180.52.26"


//        이 {  } 안에 적는 코드들은 다른 클래스에서 ServerUtil.변수/기능 활용 가능.
//        handler : 화면단에서 적어주는, 응답을 어떻게 처리할지 대처 방안이 담긴 인터페이스 변수.

        fun postRequestLogin( email: String, pw: String, handler: JsonResponseHandler? ) {

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


//                    화면단에서, 응답에 대한 처리방안을 제시했다면 (handler가 null 아니라면 - 실체가 있다면)
//                    처리방법대로 하도록 명령.

                    handler?.onResponse(jsonObj)


                }

            })

        }

//        회원가입 기능

        fun putRequestSignUp(email: String, pw: String, nickname: String, handler: JsonResponseHandler? ) {

            val urlString = "${BASE_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()


            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            })

        }

//        중복 확인 기능

        fun getRequestDuplCheck(type: String, value: String, handler: JsonResponseHandler?) {

//            1. 어디로 가야하는가? GET-query 파라미터 => 어디로? + 어떤 데이터? 한번에 조합된 형태.
//              => 만들때도 같이 만들어야함.
//              => 어디로가는가? 본체 => 파라미터 첨부까지. => url을 만들고 가공(build)

            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("type", type)
            urlBuilder.addEncodedQueryParameter("value", value)

            val urlString = urlBuilder.toString()

            Log.d("최종주소", urlString)


//            2. 어디로 + 어떤데이터? 완료된 상태. -> Request 만들자.
            val request = Request.Builder()
                .url(urlString)  // 어디로 넣으면 -> 파라미터도 같이 들어감.
                .get() // get방식은 파라미터 변수를 따로 받지 않는다. url에 다 있으니까.
                .build()

//            3. Request 완성 => 서버에 호출 하면 된다. Client로 동작.

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            } )

        }

//        내 정보 조회하기
//        토큰 첨부 필요 -> ContextUtil 활용 -> Context를 이용하는 클래스 -> 재료로 Context 넣어줄 필요가 있음.
//        토큰 첨부 API를 실행하는 함수의 재료로, context를 받아오자.
//        대부분의 서버관련 함수는 context: Context를 재료로 맨앞에 받아두면 편하다.

        fun getRequestMyInfo( context: Context,  handler: JsonResponseHandler? ) {

            val urlBuilder = "${BASE_URL}/user_info".toHttpUrlOrNull()!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter()  // 이 기능은 쿼리파라미터 첨부 없음.

            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getToken(context))
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            } )

        }



//        메인 화면에 필요한 데이터 조회 (토론 주제 목록도 조회)

        fun getRequestMainInfo( context: Context,  handler: JsonResponseHandler? ) {

            val urlBuilder = "${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter()  // 이 기능은 쿼리파라미터 첨부 없음.

            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getToken(context))
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            } )

        }


    }

}