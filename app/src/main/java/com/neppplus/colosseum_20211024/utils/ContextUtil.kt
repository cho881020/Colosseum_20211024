package com.neppplus.colosseum_20211024.utils

import android.content.Context

class ContextUtil {

    companion object {

        private val prefName = "ColosseumPref"

        private val TOKEN = "TOKEN"

//        자동로그인 체크여부 저장
        private val AUTO_LOGIN = "AUTO_LOGIN"

//        자동로그인 - setter / getter 작성

        fun setAutoLogin( context: Context,  isAuto: Boolean ) {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN,  isAuto).apply()

        }

        fun getAutoLogin( context: Context ) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }


        // setter - 토큰 저장 기능. SAVE
        fun setToken( context: Context, token: String ) {

//            메모장을 불러내자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            불러낸 메모장에 token 값 기록.
            pref.edit().putString(TOKEN,  token).apply()

        }

        // getter - 토큰 조회 기능. LOAD

        fun getToken( context: Context ) : String {
//            메모장을 불러내자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            불러낸 메모장에서 token 값 찾아서 리턴. (결과로 지정)
            return pref.getString(TOKEN, "")!!
        }

    }

}