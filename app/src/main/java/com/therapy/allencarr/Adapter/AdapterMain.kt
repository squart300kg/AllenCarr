package com.therapy.allencarr.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.therapy.allencarr.DTO.DTOReview
import com.therapy.allencarr.R
import com.google.android.gms.ads.*

class AdapterMain(val context : Context, val review_List : ArrayList<DTOReview>, val itemClick: (DTOReview) -> Unit): RecyclerView.Adapter<AdapterMain.Holder>(){
    var TAG = "AdapterMain"
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val thumbnail = itemView?.findViewById<ImageView>(R.id.thumbnail)
        val title = itemView?.findViewById<TextView>(R.id.title)
        val frame = itemView?.findViewById<LinearLayout>(R.id.frame)
        val mainReview = itemView?.findViewById<LinearLayout>(R.id.mainReview)
        val normalReview = itemView?.findViewById<LinearLayout>(R.id.normalReview)
        val mainReviewThumbnail = itemView?.findViewById<ImageView>(R.id.mainReviewThumbnail)
        val adView = itemView?.findViewById<AdView>(R.id.adView)

        fun bind (dtoReview: DTOReview, context: Context) {
            /* dtoReviewPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
            if (dtoReview.title == "mainReview") {
                requireNotNull(mainReview).visibility = View.VISIBLE
                requireNotNull(normalReview).visibility = View.GONE
                requireNotNull(adView).visibility = View.GONE
                requireNotNull(mainReviewThumbnail).setImageDrawable(getDrawable(context, R.drawable.review))
            } else if (dtoReview.title == "ad") {
                requireNotNull(mainReview).visibility = View.GONE
                requireNotNull(normalReview).visibility = View.GONE
                requireNotNull(adView).visibility = View.VISIBLE

//                MobileAds.initialize(context)

                val adRequest = AdRequest.Builder().build()
                adView.loadAd(adRequest)
                adView.adListener = object: AdListener() {
                    override fun onAdLoaded() {
                        Log.i(TAG, "adLoaded")
                    }

                    override fun onAdFailedToLoad(adError : LoadAdError) {
                        // Code to be executed when an ad request fails.
                        Log.i(TAG, "adLoadedFailed")
                    }

                    override fun onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                        Log.i(TAG, "adLoadedOpened")
                    }

                    override fun onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                        Log.i(TAG, "adLoadedClicked")
                    }

                    override fun onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                        Log.i(TAG, "adLoadedLeft")
                    }

                    override fun onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                        Log.i(TAG, "adLoadedClosed")
                    }
                }

            } else if (dtoReview.thumbnail != "") {
                if (thumbnail != null) {
                    requireNotNull(mainReview).visibility = View.GONE
                    requireNotNull(adView).visibility = View.GONE
                    requireNotNull(normalReview).visibility = View.VISIBLE
                    Glide.with(context).load(dtoReview.thumbnail).into(thumbnail)
                }
            } else {
                thumbnail?.setImageResource(R.mipmap.ic_launcher)
            }
            title?.text = dtoReview.title
            frame?.setOnClickListener { itemClick(dtoReview) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.review_one, parent, false)

        return Holder(view)


    }

    override fun getItemCount(): Int {

        return review_List.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder?.bind(review_List[position], context)

    }

    private fun initAdView() {

    }
}

