package com.qmdroider.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    //var to count turns
    private var pressCounter = 0
    //map to record player 1 moves
    private var xDataPOne: HashMap<Int, String> = HashMap<Int, String>(5)
    //map to record player 2 moves
    private var oDataPTwo: HashMap<Int, String> = HashMap<Int, String>(4)

    //mutable live data to handle player turn text
    private val currentTurnText = MutableLiveData<String>()
    // exposed live data to be observed in activity to update the textViewTitle
    val stringCurrentTurnText: LiveData<String>
        get() = currentTurnText

    //mutable live data to handle X or O text
    private val currentTextViewXO = MutableLiveData<String>()
    //exposed live data to be observed in activity to update the textView that was tapped by each player
    val stringCurrentTextViewXO: LiveData<String>
    get() = currentTextViewXO

    //mutable live data to track the resId of the textView that was pressed
    private val currentTextViewId = MutableLiveData<Int>()
    //exposed live data to be observed in activity to update the pressed textView
    val intCurrentTextViewId: LiveData<Int>
    get() = currentTextViewId

    //mutable live data to handle the winner text
    private val winner = MutableLiveData<String>()
    //exposed live data to be observed in activity to show the winner text in an AlertDialog
    val stringWinner: LiveData<String>
        get() = winner

    //mutable live data boolean to track whether the game needs to be reset
    private val resetBoard = MutableLiveData<Boolean>()
    //exposed live data to be observed in activity to reset the game board UI
    val boolResetBoard: LiveData<Boolean>
        get() = resetBoard

    //function called by the activity when a textView is pressed
    fun textViewPressed(id: Int, tag: Int) {
        currentTextViewId.value = id
        pressCounter += 1
        if(pressCounter <= 9) {
            //check if the tile was already pressed
            if(!oDataPTwo.containsKey(tag) && !xDataPOne.containsKey(tag)) {
                if(pressCounter % 2 == 0) {
                    currentTurnText.value = "Player 1 Turn"
                    oDataPTwo[tag] = "O"
                    currentTextViewXO.value = "O"
                } else {
                    currentTurnText.value = "Player 2 Turn"
                    xDataPOne[tag] = "X"
                    currentTextViewXO.value = "X"
                }
                //run winner check algorithm
                if (pressCounter >= 5) {
                    checkWinner(xDataPOne, "X")
                }
                if(pressCounter >=6) {
                    checkWinner(oDataPTwo, "O")
                }
            }
            else {
                //undo press count if an already used tile was pressed again
                pressCounter -= 1
            }

        }
    }

    //function to reset the game
    fun reset() {
        resetBoard.value = true
        xDataPOne.clear()
        oDataPTwo.clear()
        pressCounter = 0
        currentTurnText.value = ""
        winner.value = ""
        currentTurnText.value = "Player 1 Turn"
    }

    //winner checking algorithm
    private fun checkWinner(data: HashMap<Int, String>, mark: String) {

            if(data.containsKey(1) && data.containsKey(2) && data.containsKey(3)) {
                if(data[1] == mark && data[2] == mark && data[3] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }

            }
            if(data.containsKey(4) && data.containsKey(5) && data.containsKey(6)) {
                if(data[4] == mark && data[5] == mark && data[6] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(7) && data.containsKey(8) && data.containsKey(9)) {
                if(data[7] == mark && data[8] == mark && data[9] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(1) && data.containsKey(4) && data.containsKey(7)) {
                if(data[1] == mark && data[4] == mark && data[7] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(2) && data.containsKey(5) && data.containsKey(8)) {
                if(data[2] == mark && data[5] == mark && data[8] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(3) && data.containsKey(6) && data.containsKey(9)) {
                if(data[3] == mark && data[6] == mark && data[9] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(1) && data.containsKey(5) && data.containsKey(9)) {
                if(data[1] == mark && data[5] == mark && data[9] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(data.containsKey(3) && data.containsKey(5) && data.containsKey(7)) {
                if(data[3] == mark && data[5] == mark && data[7] == mark) {
                    if(mark == "X") {
                        winner.value = "Player 1 Wins!"
                        currentTurnText.value = ""
                    } else {
                        winner.value = "Player 2 Wins!"
                        currentTurnText.value = ""
                    }
                    return
                }
            }
            if(pressCounter == 9 && (winner.value == "" || winner.value == null)) {
                winner.value = "Game Drawn!"
                reset()
            }

    }
}