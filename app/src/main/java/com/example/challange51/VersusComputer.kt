package com.example.challange51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_versus_computer.*


const val TAG = "Versus Computer"

class VersusComputer : AppCompatActivity(), ResultCallBack {

    private lateinit var tvNama: String
    private lateinit var idGambar: MutableList<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_versus_computer)

        tvNama = intent.extras?.getString(NAMA_PEMAIN).toString()
        var idImage =
            mutableListOf<ImageView>(batu, batuCom, kertas, kertasCom, gunting, guntingCom)
        pemain.text = tvNama
        var player = Pemain(tvNama)
        var player2 = Pemain("CPU")
        val suit = mutableListOf("Batu", "Gunting", "Kertas")
        val controller = Controller(this)
        idGambar = mutableListOf()

        for (id in idImage) {
            id.setOnClickListener {
                val com = (0..2).random()
                val index = id.contentDescription.toString().toInt()
                player.pilihan = suit[index]
                Log.d("PEMAIN 1", suit[index])
                idGambar.add(id)
                id.setBackgroundColor(resources.getColor(R.color.choose))
                player2.pilihan = suit[com]
                idGambar.add(id)
                Log.d("CPU", suit[com])
                when (com) {
                    0 -> batuCom.setBackgroundColor(resources.getColor(R.color.choose))
                    1 -> guntingCom.setBackgroundColor(resources.getColor(R.color.choose))
                    2 -> kertasCom.setBackgroundColor(resources.getColor(R.color.choose))
                }
                controller.checkPemenang(player, player2)
                sentuh(klik = false, idImage = idImage)
            }
        }
        repeat.setOnClickListener {
            ulang2(idImage)
            sentuh(klik = true, idImage = idImage)
            Log.d(TAG, "GAME DIULANG")
            idGambar.removeAll(idImage)
        }

        close.setOnClickListener {
            val alert = this.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("YA") { _, _ ->
                        finishAffinity()
                    }
                    setNegativeButton("Tidak") { dialog, _ ->
                        dialog.cancel()
                    }
                    setTitle("KELUAR DARI PERMAIANAN")
                    setMessage("Keluar Dari Permainan ?")
                }
                builder.create()
            }
            alert.show()
        }

        home.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NAMA_PEMAIN, pemain.text.toString())

            val alert = this.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("YA") { _, _ ->
                        startActivity(intent)
                        Log.d(TAG2, "Kembali Ke Menu")
                    }
                    setNegativeButton("Tidak") { dialog, _ ->
                        dialog.cancel()
                        Log.d(TAG2, "Tetap di Permainan")
                    }
                    setTitle("Keluar dari Permainan")
                    setMessage("Kembali Ke Menu ?")
                }
                builder.create()
            }
            alert.show()
        }

    }

    fun sentuh(klik: Boolean, idImage: MutableList<ImageView>) {
        for (id in idImage) {
            id.isClickable = klik
        }
    }

    fun ulang2(idImage: MutableList<ImageView>) {
        for (id in idImage) {
            id.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        versus.text = "VS"
        versus.setTextColor(resources.getColor(R.color.vs))
        versus.textSize = 60.0f
    }

    override fun result(bg: String) {
        when (bg) {
            "CPU" -> {
                Log.d(TAG2, "CPU Menang")
                versus.text = "CPU Menang"
                versus.setTextColor(resources.getColor(R.color.colorAccent))
                versus.textSize = 30.0f
            }
            tvNama -> {
                Log.d(TAG2, "Pemain 1 Menang")
                versus.text = "${tvNama} Menang"
                versus.setTextColor(resources.getColor(R.color.colorAccent))
                versus.textSize = 30.0f
            }

            "DRAW" -> {
                Log.d(TAG2, "Draw")
                versus.text = "DRAW"
                versus.textSize = 30.0f
                versus.setTextColor(resources.getColor(R.color.indicatorActive))
            }
        }
    }
}

