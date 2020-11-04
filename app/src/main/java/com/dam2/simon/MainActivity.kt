package com.dam2.simon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
//    val text= "Hello Toast"
//    val duration = Toast.LENGTH_SHORT
    private var game = mutableListOf<Int>()
    private var player = mutableListOf<Int>()


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val toast=Toast.makeText(applicationContext,text,duration)
//        toast.show()
        val startB: Button = findViewById(R.id.startButton)
        val resetB: Button = findViewById(R.id.resetButton)
        val combS: TextView = findViewById(R.id.infoText)

        startB.setOnClickListener {
            movSis();
            combS.text= game.toString()+"\n"
            sysPlays()
            pPlays()
        }
        resetB.setOnClickListener {
            game.clear()
            combS.text="Reinicio"
        }
    }

    private fun movSis(){
        val pos : Int
        val randInt = Random.nextInt(4)+1
        pos=game.size
        game.add(pos,randInt)
    }

    private fun sysPlays(){
        val greenB:Button=findViewById(R.id.greenButton)
        val redB:Button=findViewById(R.id.redButton)
        val yellowB:Button=findViewById(R.id.yellowButton)
        val blueB:Button=findViewById(R.id.blueButton)
        //val combS: TextView = findViewById(R.id.infoText)
        for (c in (this.game)){
            //combS.text=combS.text.toString()+c.toString()+"\n"
            when (c){
                1 -> greenB.isActivated
                2 -> redB.shadowColor
                3 -> yellowB.isPressed
                4 -> blueB.highlightColor
            }
        }
    }

    private fun pPlays(){
        val greenB:Button=findViewById(R.id.greenButton)
        val redB:Button=findViewById(R.id.redButton)
        val yellowB:Button=findViewById(R.id.yellowButton)
        val blueB:Button=findViewById(R.id.blueButton)
        val combS: TextView = findViewById(R.id.infoText)
        //combS.text=combS.text.toString()+game.lastIndex.toString()
        var j = game.size
        var pos : Int
        var opcion =0
        while (j!=0) {
            for (p in game) {

                greenB.setOnClickListener { opcion = 1 }
                redB.setOnClickListener { opcion = 2 }
                yellowB.setOnClickListener { opcion = 3 }
                blueB.setOnClickListener { opcion = 4 }
                if (p == opcion) {
                    pos = player.size
                    player.add(pos, opcion)
                } //else {
                  //  combS.text = "Fallaste"
                  //  break
                //}
            }
            j--
        }
    }

}
