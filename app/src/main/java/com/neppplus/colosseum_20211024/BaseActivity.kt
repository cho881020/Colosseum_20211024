package com.neppplus.colosseum_20211024

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity()  {

    val mContext = this

    lateinit var backBtn : ImageView
    lateinit var profileBtn : ImageView

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar() {

//        일반 함수를 물려준다 -> 그 실행 내용까지 같이 내려줌.
//        자식 (다른 화면) 에서는 -> 이 함수를 실행 만 하면 바로 사용 가능.

        val defActionBar = supportActionBar!!
        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        defActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolbar = defActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0, 0)


        backBtn = defActionBar.customView.findViewById(R.id.backBtn)
        profileBtn = defActionBar.customView.findViewById(R.id.profileBtn)

        profileBtn.setOnClickListener { 
//            향후 작성 : 프로필 화면으로 진입
        }

        backBtn.setOnClickListener {

//            모든 화면의 백버튼은 기능이 동일.
//            화면 종료

            finish()

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
//        여기에 적는 코드? -> 자식 (다른 화면)들의 super.onCreate => 실행되는 내용
        
//        모든 화면이 만들어질때 ->  액션바가 있다면 -> 액션바 커스터마이징 기능 실행

        supportActionBar?.let {
            setCustomActionBar()
        }


    }

}