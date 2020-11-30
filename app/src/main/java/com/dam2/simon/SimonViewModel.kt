package com.dam2.simon

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SimonViewModel: ViewModel() {

    //We add the two game variables
    val systemMoves = MutableLiveData<MutableList<Int>>()
    val playerMoves = MutableLiveData<MutableList<Int>>()
    //A variable to deactivate a button
    val buttonStatus = MutableLiveData<Boolean>()

    //Initialice both variables
    init {
        systemMoves.value= mutableListOf()
        playerMoves.value= mutableListOf()
        buttonStatus.value=true
    }

    //Method to force notify the observer about a change on a mutablelist
    fun <T> MutableLiveData <T>.notifiObserver(){
        this.value=this.value
    }

    //Method that adds a random value to the system variable
    fun systemPlays(){
        //adding coroutine here cause i dont know where else to do it
        CoroutineScope(Dispatchers.Main).launch {
            val pos: Int
            val randInt = Random.nextInt(4) + 1
            pos = systemMoves.value!!.size
            systemMoves.value!!.add(pos, randInt)
            //forces the variable update calling the observer
            delay(1000)
            systemMoves.notifiObserver()
            buttonStatus.value = false
            //Log.d("FuncionSystem", systemMoves.value.toString())
        }
    }

    //Method to restart variables
    fun fullReset(){
        systemMoves.value!!.clear()
        playerMoves.value!!.clear()
        buttonStatus.value=true
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
        //Log.d("FuncionSystem",playerMoves.value.toString())
    }

    //Method to check if both variables are the same
    fun checkMoves(): Boolean{
        return systemMoves.value==playerMoves.value
    }

    //Uses method checkMoves to interact with user
    fun play(check: Boolean):String{
        if (check==true){
            buttonStatus.value=true
            return "All Good press Start to keep going."
        } else {
            buttonStatus.value=true
            return "You Failed. Never come back!"
        }
    }

}


