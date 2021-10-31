package com.neppplus.colosseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_20211024.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.okBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.pwEdt.text.toString()
            val inputNickname = binding.nicknameEdt.text.toString()

//            입력 데이터를 => 서버의 회원가입 기능에 요청. => ServerUtil 함수 활용. => 함수가 아직 없으니 추가로 만들자.
//            ServerUtil.

        }

    }

    override fun setValues() {

    }

}