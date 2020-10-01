package zak.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var diNumber = 0
    private val goalTotal = 100
    private var turnTotal = 0
    private var p1Total = 0
    private var p2Total = 0
    private var player1Active = true
    private var isPvEGame = false
    private var passed = false
    private var newGamePvP = false
    private var newGamePvE = false
    private var gameTypePvP = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
        textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))

        if (gameTypePvP) {
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
        } else {
            if (player1Active) {
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
            } else {
                if (willPlay(goalTotal, turnTotal, p2Total)) {
                    buttonRollOnCLick()
                } else {
                    buttonPassOnClick()
                }
            }
        }
    }

    private fun buttonRollOnCLick() {
        diNumber = (1..6).random()
        turnTotal += diNumber
        updateUI()
    }


    private fun buttonPassOnClick() {
        if (player1Active) {
            passed = true
            p1Total += turnTotal
            updateUI()
        } else {
            passed = true
            p2Total += turnTotal
            updateUI()
        }
    }


    private fun updateUI() {
        if (newGamePvP) {
            textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
            textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
            textViewP1Total.text = "0"
            textViewP2Total.text = "0"
            gameTypePvP = true
        } else if (newGamePvE) {
            textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
            textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
            textViewP1Total.text = "0"
            textViewP2Total.text = "0"
            gameTypePvP = false
        } else if (passed) {
            if (player1Active) {
                textViewP1Total.text = p1Total.toString()
                textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                player1Active = false
            } else {
                textViewP2Total.text = p2Total.toString()
                textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                player1Active = true
            }
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
            passed = false
            turnTotal = 0
        } else {
            if (diNumber != 1) {
                textViewTurnScore.text = diNumber.toString()
                textViewTurnTotal.text = turnTotal.toString()
            } else {
                turnTotal = 0
                textViewTurnScore.text = "0"
                textViewTurnTotal.text = "0"
                if (player1Active) {
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    player1Active = false
                } else {
                    textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                    textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                    player1Active = true
                }
            }
        }
    }


    private fun buttonPvPOnClick() {
        newGamePvP = true
        turnTotal = 0
        p1Total = 0
        p2Total = 0
        player1Active = true
        updateUI()
        newGamePvP = false
    }


    private fun buttonPvEOnClick() {
        newGamePvE = true
        turnTotal = 0
        p1Total = 0
        p2Total = 0
        player1Active = true
        updateUI()
        newGamePvE = false
    }

    private fun willPlay(goalTotal: Int, turnTotal: Int, currentTotal: Int): Boolean {
        if (currentTotal + turnTotal >= goalTotal) {
            return false
        } else {
            return turnTotal < 10
        }
    }
} //end class