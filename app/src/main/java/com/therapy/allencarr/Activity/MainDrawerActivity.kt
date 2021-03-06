package com.therapy.allencarr.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.therapy.allencarr.Adapter.AdapterMain
import com.therapy.allencarr.DTO.DTOReview
import com.therapy.allencarr.R
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.app_bar_main_drawer.*
import kotlinx.android.synthetic.main.content_main_drawer.*
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.google.android.gms.ads.MobileAds


class MainDrawerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    val TAG = "MainDrawerActivity"
    var review_list = arrayListOf<DTOReview>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(com.therapy.allencarr.R.layout.activity_main_drawer)
//        youtube_view.initialize(getString(com.example.allencarr.R.string.youtubeApi_key), this)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toggle.getDrawerArrowDrawable().setColor(getColor(R.color.appbar_color))
        } else {
            toggle.getDrawerArrowDrawable()
                .setColor(resources.getColor(R.color.appbar_color))
        }

        // 애드옵SDK사용을 위한 초기화
        MobileAds.initialize(this)

        // 첫 메인페이지 문구 강조
        var ssb = SpannableStringBuilder("87%의 금연 성공률을 자랑하는 알렌카에선 다음과 같이 말합니다.\n\n\"의지력 금연법의 한계는 너무 명확하다.\"")
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#ff0000")), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan((RelativeSizeSpan(1.3f)), 35, ssb.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan((StyleSpan(Typeface.BOLD)),  35, ssb.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        landing_text1.text = ssb


        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        //TODO :: 유튜브 영상 더보기
//        moreView_youTube.setOnClickListener(this)
        //TODO :: 알렌카 후기 영상 초기화
//        init_reviewList()
        //TODO :: 알렌카 자세히 보기 클릭
        moreView_allenCarr.setOnClickListener(this)
        //TODO :: 알렌카 환불 보증제도 보기 클릭
//        return_system1.setOnClickListener(this)

        myReview.setOnClickListener(this)

        //TODO :: 로그인 버튼 클릭 OR 마이페이지 클릭
        val header = navView.getHeaderView(0)
        var sp = getSharedPreferences("Login", Context.MODE_PRIVATE)
        var email = sp.getString("email", "")
        Log.i(TAG + " email : ", email)


        //TODO :: 테라피 후기 리스트를 위한 리사이클러뷰 초기화
        Log.i(TAG, "adapterMain들어가기 직전")
        val adapterMain = AdapterMain(this)
        {
                dtoReview -> startActivity(YouTubeIntents.createPlayVideoIntentWithOptions(this, dtoReview.videoID, true, false))
        }
        RecyclerView.adapter = adapterMain

        val layoutManager = LinearLayoutManager(this)
        RecyclerView.layoutManager = layoutManager
        RecyclerView.setHasFixedSize(true)


    }


    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1!!.cueVideo("kxakLJZex2k")
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(p0: View?)
    {

        when(p0)
        {
            myReview -> {
                var intent = Intent(this, MoreView::class.java)
                intent.putExtra("type", "myReview")
                startActivity(intent)
        }

            //TODO :: 알렌카 예약하기 클릭
            moreView_allenCarr ->
            {
                //TODO :: 예약을 위해서 로그인페이지로 넘겨준다.
                var intent = Intent(this, MoreView::class.java)
                intent.putExtra("type", "moreView")
                startActivity(intent)
            }

        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val id = item.itemId


        //TODO :: 알렌카 자주하는 질문
        if (id == R.id.nav_question)
        {
            Log.i(TAG, "nav_question")
            intent = Intent(this, MoreView::class.java)
            intent.putExtra("type", "return_system")
            startActivity(intent)
        }
        //TODO :: 알렌카 유튜브 보기
        else if (id == R.id.nav_youtube)
        {
            Log.i(TAG, "nav_youtube")
            startActivity(YouTubeIntents.createChannelIntent(this, getString(com.therapy.allencarr.R.string.channelID)))
        }
        //TODO :: 알렌카 카페 가기
        else if (id == R.id.nav_naverCafe)
        {
            Log.i(TAG, "nav_naverCafe")
            intent = Intent(this, MoreView::class.java)
            intent.putExtra("type", "naver")
            startActivity(intent)
        }
        //TODO :: 알렌카 페이스북 가기
        else if (id == R.id.nav_facebook)
        {
            Log.i(TAG, "nav_facebook")
            intent = Intent(this, MoreView::class.java)
            intent.putExtra("type", "facebook")
            startActivity(intent)
        }
        //TODO :: 선물
        else if (id == R.id.present)
        {
            Log.i(TAG, "nav_homepage")
            intent = Intent(this, MoreView::class.java)
            intent.putExtra("type", "present")
            startActivity(intent)
        }
        //TODO :: 알렌카 오픈채팅방 가기
        else
        {
            Log.i(TAG, "nav_kakao_openChattingRoom")
            intent = Intent(this, MoreView::class.java)
            intent.putExtra("type", "kakao")
            startActivity(intent)
        }
         return true
    }

    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_drawer, menu)
        return true
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

}
