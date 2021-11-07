package com.neppplus.colosseum_20211024

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_20211024.databinding.ActivitySplashBinding
import com.neppplus.colosseum_20211024.utils.ContextUtil

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler( Looper.getMainLooper() )

        myHandler.postDelayed( {

//           자동 로그인을 해도 되는지 검사.
//           1. 토큰값이 저장된게 있는가?
//           2. (차후 작성)  그 토큰이 실제로 유효한가? => 데이터를 받아올 수 있는 토큰?

            val myIntent : Intent

           if ( ContextUtil.getToken(mContext) != "" ) {

//               저장된 토큰이 있다!
//                -> 메인화면으로 넘어가자.

               myIntent = Intent(mContext, MainActivity::class.java)

           }
            else {

//                저장된 토큰이 "" 이다! => 없다!
//                  -> 새로 로그인 해야함. -> 로그인 화면으로 넘어가자.
                myIntent = Intent(mContext, LoginActivity::class.java)

           }

            startActivity(myIntent)

            finish()

        },  2500 )


    }

}