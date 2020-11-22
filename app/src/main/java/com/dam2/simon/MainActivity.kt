package com.dam2.simon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
//    val text= "Hello Toast"
//    val duration = Toast.LENGTH_SHORT
    private var game = mutableListOf<Int>()
//    private var player = mutableListOf<Int>()


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        var player = mutableListOf<Int>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding viewmodel
        val simonModel by viewModels<SimonViewModel>()
//        val toast=Toast.makeText(applicationContext,text,duration)
//        toast.show()
        val startButton: Button = findViewById(R.id.startButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val checkButton: Button = findViewById(R.id.checkButton)
        val outputText: TextView = findViewById(R.id.infoText)

        val greenB:Button=findViewById(R.id.greenButton)
        val redB:Button=findViewById(R.id.redButton)
        val yellowB:Button=findViewById(R.id.yellowButton)
        val blueB:Button=findViewById(R.id.blueButton)

        simonModel.systemMoves.observe(this,
            Observer{ newPlay -> outputText.text=systemMoves().toString()})

        greenB.setOnClickListener {
            addPlayerMove(player,1) }
        redB.setOnClickListener {
            addPlayerMove(player,2) }
        yellowB.setOnClickListener {
            addPlayerMove(player,3) }
        blueB.setOnClickListener {
            addPlayerMove(player,4) }

        startButton.setOnClickListener {
            simonModel.systemPlays()
            //systemMoves();
            //outputText.text= game.toString()+"\n Do ur moves and press Check."
        }
        resetButton.setOnClickListener {
            game.clear()
            player.clear()
            outputText.text="Reinicio"
        }

        checkButton.setOnClickListener {
            checkMoves(player,game)
            player.clear()
        }


    }

    private fun systemMoves(){
        val pos : Int
        val randInt = Random.nextInt(4)+1
        pos=game.size
        game.add(pos,randInt)
    }

    private fun checkMoves(player: MutableList<Int>, game: MutableList<Int>){
        val outputText: TextView = findViewById(R.id.infoText)
        outputText.text="Checking player moves\n"
        outputText.text=player.toString()+"\n"
        if (game==player) {
            outputText.text = outputText.text.toString() + "All good hit Start for a new round"
        }else {
            outputText.text= outputText.text.toString()+"You made a mistake, hit reset to start again."
        }
    }

    /* NOT WORKING ATM private fun systemPlays(){
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
    }*/

    private fun addPlayerMove(player: MutableList<Int>, number: Int ){
        when (number) {
            1 -> player.add(1)
            2 -> player.add(2)
            3 -> player.add(3)
            else -> player.add(4)
        }
        val outputText: TextView = findViewById(R.id.infoText)
        outputText.text=player.toString()
    }

}
