package com.therapy.allencarr.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_more_view2.*
import android.webkit.WebView
import android.net.Uri
import java.net.URISyntaxException


class MoreView : AppCompatActivity() ,View.OnClickListener{

    val TAG = "MoreVIewActivity"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(com.therapy.allencarr.R.layout.activity_more_view2)

        val intent = getIntent()
        val type = intent.getStringExtra("type")

        if ( type == "myReview")
        {
            loadMyReview()
        }
        else if(type == "moreView")
        {
            loadAllencar()
            Log.i(TAG + " type : ", type)
        }
        else if(type == "present")
        {
            loadPresent()
        }
        else if ( type == "naver")
        {
            loadNaver()
            Log.i(TAG + " type : ", type)
        }
        else if ( type == "facebook")
        {
            loadFacebook()
            Log.i(TAG + " type : ", type)
        }
        else if (type == "kakao")
        {
            loadOpenChatting()
            Log.i(TAG + " type : ", type)
        }
        else if (type == "return_system")
        {
            loadReturnVideo()
            Log.i(TAG + " type : ", type)
        }
        else
        {
            Log.i(TAG + " type : ", "없음")
        }

//        TODO :: 알렌카 예약페이지 클릭
//        book_allenCarr.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
//        when(p0)
//        {
//            //TODO :: 알렌카 예약하기 클릭
//            book_allenCarr ->
//            {
//                //TODO :: 지인할일을 받을 것인가에 대해 물어본다.
////                startActivity(Intent(this, PopUp_IsRecommend::class.java))
//                startActivity(Intent(this, BookActivity::class.java))
//            }
//        }
    }

    fun loadOpenChatting()
    {
        class MyWebViewClient : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (url != null && url.startsWith("intent://")) {
                    try {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        val existPackage =
                            packageManager.getLaunchIntentForPackage(intent.getPackage()!!)
                        if (existPackage != null) {
                            startActivity(intent)
                        } else {
                            val marketIntent = Intent(Intent.ACTION_VIEW)
                            marketIntent.data =
                                Uri.parse("market://details?id=" + intent.getPackage()!!)
                            startActivity(marketIntent)
                        }
                        return true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                } else if (url != null && url.startsWith("market://")) {
                    try {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        if (intent != null) {
                            startActivity(intent)
                        }
                        return true
                    } catch (e: URISyntaxException) {
                        e.printStackTrace()
                    }

                }
                view.loadUrl(url)
                return false
            }
        }
        val webView = webView
        webView.webViewClient = MyWebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.kakao_openChatting_url))
    }
    fun loadPresent()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.myPresent))
    }

    fun loadMyReview()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.myReviewURL))
    }

    fun loadAllencar()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.allencarURL))
    }

    fun loadReturnVideo()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.return_videoURL))
    }
    fun loadNaver()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.naverURL))
    }
    fun loadFacebook()
    {
        val webView = webView
        webView.webViewClient = WebViewClient()
        var mWebSettings = webView.settings

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true

        webView.loadUrl(getString(com.therapy.allencarr.R.string.facebookURL))
    }
}
