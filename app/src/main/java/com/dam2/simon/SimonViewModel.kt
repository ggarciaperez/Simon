package com.dam2.simon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SimonViewModel: ViewModel() {

    //We add the two game variables
    val systemMoves = MutableLiveData<MutableList<Int>>()
    val playerMoves = MutableLiveData<MutableList<Int>>()

    init {
        systemMoves.value!!.clear()
        playerMoves.value!!.clear()
    }

    fun systemPlays(){
        val pos : Int
        val randInt = Random.nextInt(4)+1
        pos= systemMoves.value!!.size
        this.systemMoves.value!!.add(pos,randInt)
    }




}