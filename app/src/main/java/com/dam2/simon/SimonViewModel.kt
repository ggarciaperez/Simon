package com.dam2.simon

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SimonViewModel: ViewModel() {

    //We add the two game variables
    val systemMoves = MutableLiveData<MutableList<Int>>()
    val playerMoves = MutableLiveData<MutableList<Int>>()

    //Initialice both variables
    init {
        systemMoves.value= mutableListOf()
        playerMoves.value= mutableListOf()
    }

    //Method to force notify the observer about a change on a mutablelist
    fun <T> MutableLiveData <T>.notifiObserver(){
        this.value=this.value
    }

    //Method that adds a random value to the system variable
    fun systemPlays(){
        val pos : Int
        val randInt = Random.nextInt(4)+1
        pos= systemMoves.value!!.size
        this.systemMoves.value!!.add(pos,randInt)
        //forces the variable update calling the observer
        systemMoves.notifiObserver()
        Log.d("FuncionSystem",systemMoves.value.toString())
    }

    //Method to restart variables
    fun fullReset(){
        systemMoves.value!!.clear()
        playerMoves.value!!.clear()
    }

    //Method to only restar player variable
    fun playerReset(){
        playerMoves.value!!.clear()
    }

    //Method to fill the player variable depending on button pressed
    fun addPlayerMove(playerMoves: MutableLiveData<MutableList<Int>>, number: Int ){
        when (number) {
            1 -> playerMoves.value!!.add(1)
            2 -> playerMoves.value!!.add(2)
            3 -> playerMoves.value!!.add(3)
            else -> playerMoves.value!!.add(4)
        }
        //forces the variable update calling observer
        playerMoves.notifiObserver()
    }

    //Method to check if both variables are the same
    fun checkMoves(system: MutableLiveData<MutableList<Int>>, player: MutableLiveData<MutableList<Int>>): Boolean{
        return system==player
    }

}


