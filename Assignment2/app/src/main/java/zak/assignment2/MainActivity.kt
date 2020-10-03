package zak.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var diNumber = 0
    private val goalTotal = 100
    private var turnTotal = 0
    private var p1Total = 0
    private var p2Total = 0
    private var player1Active = true
    private var passed = false
    private var newGame = false
    private var gameTypePvP = true
    private val history = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
        textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))

        buttonRoll.setOnClickListener {
            buttonRollOnCLick()
        }
        buttonPass.setOnClickListener {
            buttonPassOnClick()
        }
        buttonPvP.setOnClickListener() {
            buttonPvPOnClick()
        }
        buttonPvE.setOnClickListener() {
            buttonPvEOnClick()
        }
        recyclerView.adapter = HistoryDataAdapter(history)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }



    private fun computerPlay() {
        while (!player1Active) {
            if (willPlay(goalTotal, turnTotal, p2Total)) {
                buttonRollOnCLick()
            }
            else {
                buttonPassOnClick()
            }
        }
    }



    private fun buttonRollOnCLick() {
        diNumber = (1..6).random()
        turnTotal += diNumber
        updateUI()
        if (diNumber == 1 && !gameTypePvP && player1Active) {
            turnTotal = 0
            player1Active = !player1Active
            computerPlay()
        }
        else if (!player1Active && !gameTypePvP) {
            computerPlay()
        }
        else if (diNumber == 1){
            turnTotal = 0
            player1Active = !player1Active
        }
    }



    private fun buttonPassOnClick() {
        passed = true
        if (player1Active && gameTypePvP) {
            p1Total += turnTotal
            updateUI()
            player1Active = false
        } else if (player1Active && !gameTypePvP){
            p1Total += turnTotal
            updateUI()
            player1Active = false
            computerPlay()
        }
        else {
            p2Total += turnTotal
            updateUI()
            player1Active = true
        }
        passed = false
        turnTotal = 0
    }



    private fun buttonPvPOnClick() {
        newGame = true
        turnTotal = 0
        p1Total = 0
        p2Total = 0
        player1Active = true
        updateUI()
        newGame = false
        gameTypePvP = true
    }



    private fun buttonPvEOnClick() {
        newGame = true
        turnTotal = 0
        p1Total = 0
        p2Total = 0
        player1Active = true
        updateUI()
        newGame = false
        gameTypePvP = false
    }



    private fun updateUI() {
        if (newGame) {
            textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
            textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
            textViewP1Total.text = "0"
            textViewP2Total.text = "0"
            history.add("A new game has been started")
        } else if (passed) {
            if (player1Active) {
                if (p1Total >= 100) {
                    player1Active = true
                    p1Total = 0
                    p2Total = 0
                    history.add("Player 1 won")
                    history.add("Please select a new game")
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    textViewTurnScore.text = "0"
                    textViewTurnTotal.text = "0"
                    textViewP1Total.text = "0"
                    textViewP2Total.text = "0"
                }
                else {
                    history.add("Player 1 passed")
                    textViewP1Total.text = p1Total.toString()
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                }
            } else {
                if (p2Total >= 100) {
                    player1Active = true
                    p1Total = 0
                    p2Total = 0
                    history.add("Player 2 won")
                    history.add("Please select a new game")
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    textViewTurnScore.text = "0"
                    textViewTurnTotal.text = "0"
                    textViewP1Total.text = "0"
                    textViewP2Total.text = "0"
                }
                else {
                    history.add("Player 2 passed")
                    textViewP2Total.text = p2Total.toString()
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                }
            }
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
        } else {
            if (diNumber != 1) {
                textViewTurnScore.text = diNumber.toString()
                textViewTurnTotal.text = turnTotal.toString()
                if (player1Active) {
                    history.add("Player 1 rolled $diNumber")
                }
                if (!player1Active){
                    history.add("Player 2 rolled $diNumber")
                }
            } else {
                textViewTurnScore.text = "0"
                textViewTurnTotal.text = "0"
                if (player1Active) {
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    history.add("Player 1 pigged out")
                } else {
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    history.add("Player 2 pigged out")
                }
            }
        }
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scrollToPosition(history.size - 1)
    }



    private fun willPlay(goalTotal: Int, turnTotal: Int, currentTotal: Int): Boolean {
        if (currentTotal + turnTotal >= goalTotal) {
            return false
        } else {
            return turnTotal < 10
        }
    }
} //end class