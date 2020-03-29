package com.example.challange51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_slide.*
import kotlinx.android.synthetic.main.slider_image.*

//const val EXTRA_MESSAGE ="com.example.challange51"
class SlideActivity : AppCompatActivity() {

    lateinit var et : String
    private val SlideAdapter = SlideAdapter(
        listOf(
            IntroSlide(
                "Bermain suit melawan sesama pemain",
                R.drawable.landing1
            ),
            IntroSlide(
                "Bermain suit melawan komputer",
                R.drawable.landing2
            ),
            IntroSlide(
                "Tuliskan Namamu di bawah",
                R.drawable.profil
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        slideImage.adapter = SlideAdapter
        setupIndicator()
        setCurrentIndicator(0)
        slideImage.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun setupIndicator(){
        val indicator = arrayOfNulls<ImageView>(SlideAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicator.indices){
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply { this?.setImageDrawable(ContextCompat.getDrawable(
                applicationContext,
                R.drawable.indicator)
            )
                this?.layoutParams = layoutParams
                indicatorSlide.addView(indicator[i])
            }

        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorSlide.childCount
        for (i in 0 until childCount){
            val imageView = indicatorSlide[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indiactive
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator)
                )
            }
        }
        next.setOnClickListener{
            et = editNama.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NAMA_PEMAIN, et)
            Log.d(TAG,et)
            startActivity(intent)
            }
        }
    }

