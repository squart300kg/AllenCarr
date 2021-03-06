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
import java.lang.RuntimeException

class AdapterMain(val context : Context, val itemClick: (DTOReview) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var TAG = "AdapterMain"
    var review_list: ArrayList<DTOReview> = ArrayList()

    private val NORMAL_REVIEW_ITEM_TYPE = 0
    private val FAMOUS_REVIEW_ITEM_TYPE = 1
    private val ADS_ITEM_TYPE = 2

    init {

        init_reviewList()

    }
    inner class NormalHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val thumbnail = itemView?.findViewById<ImageView>(R.id.thumbnail)
        val title = itemView?.findViewById<TextView>(R.id.title)
        val frame = itemView?.findViewById<LinearLayout>(R.id.frame)

    }

    inner class FamousHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) { }

    inner class ADSHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val adView = itemView?.findViewById<AdView>(R.id.adView)

    }


    override fun getItemViewType(position: Int): Int {

        if (review_list[position].type == "famous") {
            return FAMOUS_REVIEW_ITEM_TYPE
        } else if (review_list[position].type == "ad") {
            return ADS_ITEM_TYPE
        } else {
            return NORMAL_REVIEW_ITEM_TYPE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view: View?

        when (viewType) {
            ADS_ITEM_TYPE -> {
                view = LayoutInflater.from(context).inflate(R.layout.ads_view, parent, false)
                return ADSHolder(view)
            }
            NORMAL_REVIEW_ITEM_TYPE -> {
                view = LayoutInflater.from(context).inflate(R.layout.review_one, parent, false)
                return NormalHolder(view)
            }
            FAMOUS_REVIEW_ITEM_TYPE -> {
                view = LayoutInflater.from(context).inflate(R.layout.famous_review, parent, false)
                return FamousHolder(view)
            }
            else -> {
                return throw RuntimeException("예측되지 않는 ViewType입니다.")
            }
        }
    }

    override fun getItemCount(): Int {

        return review_list.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var review_list = review_list[position]

        when (holder.itemViewType) {
            NORMAL_REVIEW_ITEM_TYPE -> {
                val normalReviewHolder = holder as NormalHolder

                if (normalReviewHolder.thumbnail != null) {
                    Glide.with(context).load(review_list.thumbnail).into(normalReviewHolder.thumbnail)
                }
                normalReviewHolder.title?.text = review_list.title
                normalReviewHolder.frame?.setOnClickListener{ itemClick(review_list) }
            }

            FAMOUS_REVIEW_ITEM_TYPE -> {
                val famousReviewHolder = holder as FamousHolder
//                famousReviewHolder.famous_review!.setBackgroundResource(R.drawable.review)
            }

            ADS_ITEM_TYPE -> {
                val ADSHolder = holder as ADSHolder

                val adRequest = AdRequest.Builder().build()
                ADSHolder.adView!!.loadAd(adRequest)
                ADSHolder.adView!!.adListener = object: AdListener() {
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
            } else -> {
            Log.i(TAG, "else ?")
        }
        }

    }

    private fun init_reviewList(){
//        val dtoReview0 = DTOReview("메인페이지", "https://i.ytimg.com/vi/iwqvsDI6LMs/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAbReugNxXJL1lKP01ab7mG4qNXSA", "iwqvsDI6LMs")

//        val dtoMainReview = DTOReview("mainReview","mainReview","mainReview")
        val famousView = DTOReview("famous", "famous", "famous",  "famous")

        val dtoReview1 = DTOReview("하루 3갑 16년 흡연 경력 이동훈님 후기", "https://i.ytimg.com/vi/iwqvsDI6LMs/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAbReugNxXJL1lKP01ab7mG4qNXSA", "iwqvsDI6LMs")
        val dtoReview2 = DTOReview("하루 3갑 25년 흡연 경력 김호준님 후기", "https://i.ytimg.com/vi/C0LL7UXgizs/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAT9df35RP9HPNjY0iGwOzlUq4acw", "C0LL7UXgizs")
        val dtoReview8 = DTOReview("하루 2갑 22년 흡연 경력 박광수님 후기", "https://i.ytimg.com/vi/uN8tk8DLOis/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBJrT02Oi_i2prj9xhZzDS1nkTCQA", "uN8tk8DLOis")
        val dtoReview11 = DTOReview("하루 2갑 20년 흡연 경력 김동섭님 후기", "https://i.ytimg.com/vi/ahxvwoO88pM/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBoQ-g3ITm60RwfHXRhbma3jLj1IQ", "ahxvwoO88pM")

        val adRView = DTOReview("ad", "ad", "ad",  "ad")

        val dtoReview13 = DTOReview("하루 2갑 22년 흡연 경력 이동채님 후기", "https://i.ytimg.com/vi/qk4XAob-GPc/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLB8_ESpgEQMDVyLHVTrbbz3Ot4vfg", "qk4XAob-GPc")
        val dtoReview3 = DTOReview("45년 흡연 경력 김준성님 후기", "https://i.ytimg.com/vi/sQoqnYFz6yo/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCBBOvSG7drlxM8-taIpHppeRYJjQ", "sQoqnYFz6yo")
        val dtoReview4 = DTOReview("하루 1갑 26년 흡연 경력 김태수님 후기", "https://i.ytimg.com/vi/rQrEuKemHJk/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLArvwuhIxg3hAvIp_tegpmDNjv-sQ", "rQrEuKemHJk")
        val dtoReview5 = DTOReview("23년 흡연 경력 김지성님 후기", "https://i.ytimg.com/vi/EVW0jk05lbA/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLDV0CuI_0Eu4BW3m9n9Xu5AGzImzw", "EVW0jk05lbA")
        val dtoReview6 = DTOReview("임준식님, 알렌카의 금연테라피 후기", "https://i.ytimg.com/vi/Gn5BjL7Rtdo/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCciF0EEo3z3y8BI2DIGzU8wUHMRQ", "Gn5BjL7Rtdo")
        val dtoReview7 = DTOReview("하루 1갑 30년 흡연 경력 변정윤님 후기", "https://i.ytimg.com/vi/owgoXDsJ4_U/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBdyHoTQdZkp5fLS1iqECGa93s13g", "owgoXDsJ4_U")
        val dtoReview9 = DTOReview("30년 흡연 경력 이상용님 후기", "https://i.ytimg.com/vi/CABwb-0M8Zc/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBcVmo9f4WyniDqDuoTQs4flFW_bQ", "CABwb-0M8Zc")
        val dtoReview10 = DTOReview("21년 흡연 경력 박지훈님 후기", "https://i.ytimg.com/vi/WucrK7fq8HE/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBi-Z_YObJw8XOrxbO3xWR3mAlWMw", "WucrK7fq8HE")
        val dtoReview12 = DTOReview("흡연 경력 30년 김은선님 후기", "https://i.ytimg.com/vi/vTRgtAOjsIs/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCfgB7LfAk26fqR8djFgWZYxSagTA", "vTRgtAOjsIs")
        val dtoReview14 = DTOReview("하루 1.5갑 15년 흡연 경력 김고은님 후기", "https://i.ytimg.com/vi/TzxAZL-juv8/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAcTNPuEseoZiVHSVOg8wRdzu8uyw", "TzxAZL-juv8")



//        review_list.add(dtoReview0)
//        review_list.add(dtoMainReview)

        review_list.add(famousView)

        review_list.add(dtoReview1)
        review_list.add(dtoReview2)
        review_list.add(dtoReview8)
        review_list.add(dtoReview11)

        review_list.add(adRView)

        review_list.add(dtoReview13)
        review_list.add(dtoReview3)
        review_list.add(dtoReview4)
        review_list.add(dtoReview5)
        review_list.add(dtoReview7)
        review_list.add(dtoReview9)
        review_list.add(dtoReview10)

        review_list.add(dtoReview12)
        review_list.add(dtoReview14)

    }
}

