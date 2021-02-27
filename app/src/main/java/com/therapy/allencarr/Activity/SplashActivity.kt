package com.therapy.allencarr.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.therapy.allencarr.R.layout.activity_splash)

        val hd = Handler()
        hd.postDelayed(SplashHandler(), 4000)

    }
    inner class SplashHandler : Runnable{
        override fun run(){
            startActivity(Intent(getApplication(), MainDrawerActivity::class.java))
            finish()
        }
    }
    override fun onBackPressed()
    {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }


}
