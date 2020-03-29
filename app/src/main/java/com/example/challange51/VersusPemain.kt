package com.example.challange51

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_versus_pemain.*

const val TAG2 = "Versus Pemain"

class VersusPemain : AppCompatActivity(), ResultCallBack {

    private lateinit var tvNama: String
    private lateinit var idGambar: MutableList<ImageView>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_versus_pemain)

        tvNama = intent.extras?.getString(NAMA_PEMAIN).toString()
        var idPemain1 = mutableListOf<ImageView>(batu, kertas, gunting)
        var idPemain2 = mutableListOf<ImageView>(batu_player2, kertas_player2, gunting_player2)
        pemain.text = tvNama
        var player = Pemain(tvNama)
        var player2 = Pemain("Pemain 2")
        val suit = mutableListOf("Batu", "Gunting", "Kertas")
        var state: Boolean = false
        var controller = Controller2(this)
        idGambar = mutableListOf()

        for (id in idPemain1) {

            id.setOnClickListener {
                state = true
                var index = id.contentDescription.toString().toInt()
                idGambar.add(id)
                player.pilihan = suit[index]
                Log.d("PEMAIN 1", suit[index])
                sentuh(klik = false, idImage = idPemain1)
            }
        }

        for (id in idPemain2) {

            id.setOnClickListener {
                state = false
                var index = id.contentDescription.toString().toInt()
                player2.pilihan = suit[index]
                Log.d("PEMAIN 2", suit[index])
                idGambar.add(id)
                for (id in idGambar) {
                    id.setBackgroundColor(resources.getColor(R.color.indicatorInactive))
                }
                controller.checkPemenang(player, player2)
                sentuh(klik = false, idImage = idPemain2)
            }

        }
        repeat2.setOnClickListener {
            ulang(idGambar)
            Log.d(TAG2, "GAME DIULANG")
            sentuh(true, idImage = idPemain1)
            sentuh(true, idImage = idPemain2)
            idGambar.removeAll(idGambar)
        }

        close.setOnClickListener {
            val alert = this.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("YA") { _, _ ->
                        finishAffinity()
                        Log.d(TAG2,"Permainan Keluar")
                    }
                    setNegativeButton("Tidak") { dialog, _ ->
                        dialog.cancel()
                        Log.d(TAG2,"Tetap di Permainan")
                    }
                    setTitle("Keluar Dari Permainan")
                    setMessage("Keluar dari Permainan ?")
                }
                builder.create()
            }
            alert.show()
        }

        home.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(NAMA_PEMAIN, pemain.text.toString())

            val alert = this.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("YA") { _, _ ->
                        startActivity(intent)
                        Log.d(TAG2,"Kembali Ke Menu")
                    }
                    setNegativeButton("Tidak") { dialog, _ ->
                        dialog.cancel()
                        Log.d(TAG2,"Tetap di Permainan")
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

    fun ulang(idImage: MutableList<ImageView>) {
        for (id in idImage) {
            id.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        versus.text = "VS"
        versus.setTextColor(resources.getColor(R.color.vs))
        versus.textSize = 60.0f
    }

    override fun result(bg: String) {
        when (bg) {
            "Pemain 2" -> {
                Log.d(TAG2, "Pemain 2 Menang")
                versus.text = "Pemain 2 Menang"
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

