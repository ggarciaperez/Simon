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

//not using toast atm
//    val text= "Hello Toast"
//    val duration = Toast.LENGTH_SHORT


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding viewmodel
        val simonModel by viewModels<SimonViewModel>()
//not using toast atm
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
        }

        //calls viewModel Method to reset the game variables
        resetButton.setOnClickListener {
            simonModel.fullReset()
            outputText.text="Restarted"
        }

        // calls viewModel Method to check if the play is correct
        checkButton.setOnClickListener {
            outputText.text="Checking player moves\n"
            outputText.text=(simonModel.play(simonModel.checkMoves()))
            simonModel.playerReset()
        }

    }
}
