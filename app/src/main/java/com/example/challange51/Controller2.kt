package com.example.challange51

class Controller2 (private val callBack: ResultCallBack) {

    fun checkPemenang(player1:Pemain, player2:Pemain){
        val suit = mutableListOf("Batu","Gunting","Kertas")

        if (player1.pilihan == suit[1] && player2.pilihan == suit[0] ||
            player1.pilihan == suit[0] && player2.pilihan == suit[2] ||
            player1.pilihan == suit[2] && player2.pilihan == suit[1]
        ) {
            callBack.result(player2.nama)
        } else if (
            player1.pilihan == suit[1] && player2.pilihan == suit[2] ||
            player1.pilihan == suit[0] && player2.pilihan == suit[1] ||
            player1.pilihan == suit[2] && player2.pilihan == suit[0]
        ) {
            callBack.result(player1.nama)
        } else {
            callBack.result("DRAW")
        }
    }
}