package com.example.challange51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.slider_image.*

const val NAMA_PEMAIN = "NamPem"
class MainActivity : AppCompatActivity() {

//    var nama = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        savedInstanceState?.let {
//            nama = it.getString("nama")!!
//        }

        tvPemain.setText(intent.getStringExtra(NAMA_PEMAIN))
        tvPemain3.setText(intent.getStringExtra(NAMA_PEMAIN))

        pemainImage.setOnClickListener{
            val intent = Intent(this, VersusPemain::class.java)
            intent.putExtra(NAMA_PEMAIN, tvPemain.text.toString())
            startActivity(intent)
        }

        comMenu.setOnClickListener {
            val intent = Intent(this, VersusComputer::class.java)
            intent.putExtra(NAMA_PEMAIN, tvPemain.text.toString())
            startActivity(intent)

        }
    }

//    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState?.putString("nama", nama)
//    }
}
