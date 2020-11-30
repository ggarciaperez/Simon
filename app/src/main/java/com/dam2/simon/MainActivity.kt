package com.dam2.simon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

//Unused since change to viewModel
//    val text= "Hello Toast"
//    val duration = Toast.LENGTH_SHORT
//    private var game = mutableListOf<Int>()
//    private var player = mutableListOf<Int>()


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
//Unused since change to viewModel
//        var player = mutableListOf<Int>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding viewmodel
        val simonModel by viewModels<SimonViewModel>()
//Unused since change to viewModel
//        val toast=Toast.makeText(applicationContext,text,duration)
//        toast.show()

        //Addig variables to game control buttons and textview
        val startButton: Button = findViewById(R.id.startButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val checkButton: Button = findViewById(R.id.checkButton)
        val outputText: TextView = findViewById(R.id.infoText)

        //Adding variables for the user interaction
        val greenB:Button=findViewById(R.id.greenButton)
        val redB:Button=findViewById(R.id.redButton)
        val yellowB:Button=findViewById(R.id.yellowButton)
        val blueB:Button=findViewById(R.id.blueButton)

        simonModel.systemMoves.observe(this,
            Observer{ newSystemMove -> outputText.text=newSystemMove.toString()
            Log.d("FuncionSystem",newSystemMove.size.toString())
            })

        simonModel.playerMoves.observe(this, Observer {
                newPlayerMove -> outputText.text=newPlayerMove.toString()
        })

        simonModel.buttonStatus.observe(this, Observer {
            status-> startButton.isEnabled=status
        })

        //adjusted buttons to work with the viewModel variables.
        greenB.setOnClickListener {
            simonModel.addPlayerMove(simonModel.playerMoves,1) }
        redB.setOnClickListener {
            simonModel.addPlayerMove(simonModel.playerMoves,2) }
        yellowB.setOnClickListener {
            simonModel.addPlayerMove(simonModel.playerMoves,3) }
        blueB.setOnClickListener {
            simonModel.addPlayerMove(simonModel.playerMoves,4) }

        //calls viewModel Method to generate the system moves
        startButton.setOnClickListener {
            simonModel.systemPlays()
            //Un used since change to viewModel
            //systemMoves();
            //outputText.text= game.toString()+"\n Do ur moves and press Check."
        }

        //calls viewModel Method to reset the game variables
        resetButton.setOnClickListener {
            simonModel.fullReset()
            outputText.text="Restarted"
            //Un used since change to viewModel
            //game.clear()
            //player.clear()
        }

        // calls viewModel Method to check if the play is correct
        checkButton.setOnClickListener {
            outputText.text="Checking player moves\n"
            outputText.text=(simonModel.play(simonModel.checkMoves()))
            simonModel.playerReset()
            //Un used since change to viewModel
            //checkMoves(player,game)
            //player.clear()
        }


    }

    /* Obsolete methods since moved to viewModel

    private fun systemMoves(){
        val pos : Int
        val randInt = Random.nextInt(4)+1
        pos=game.size
        game.add(pos,randInt)
    }

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
    */


}
