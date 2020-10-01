package zak.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

/*
class Player {

    fun willPlay(goalTotal: Int, turnTotal: Int, currentTotal: Int, opponentTotal: Int): Boolean {
        return turnTotal < 10
    }
}
*/


class MainActivity : AppCompatActivity() {

    val textViewTurnTotal = findViewById<TextView>(R.id.textViewTurnTotal)
    val textViewTurnScore = findViewById<TextView>(R.id.textViewTurnScore)
    val buttonPass = findViewById<Button>(R.id.buttonPass)
    val buttonRoll = findViewById<Button>(R.id.buttonRoll)
    val textViewP1Total = findViewById<TextView>(R.id.textViewP1Total)
    val textViewP2Total = findViewById<TextView>(R.id.textViewP2Total)
    val textViewPlayer1 = findViewById<TextView>(R.id.textViewPlayer1)
    val textViewPlayer2 = findViewById<TextView>(R.id.textViewPlayer2)
    val buttonPvP = findViewById<Button>(R.id.buttonPvP)
    val buttonPvE = findViewById<Button>(R.id.buttonPvE)
    var diNumber = 0
    val goalTotal = 100
    var turnTotal = 0
    var p1Total = 0
    var p2Total = 0
    var player1Active = true
    var isPvEGame = false
    var passed = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
        textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))

        buttonRoll.setOnClickListener {
            buttonRollOnCLick()
        }
        buttonPass.setOnClickListener {
            buttonPassOnClick()
        }
    }

    private fun buttonRollOnCLick() {
        diNumber = (1..6).random()
        turnTotal += diNumber
    }



    private fun buttonPassOnClick() {
        if (player1Active) {
            passed = true
            p1Total += turnTotal
            updateUI()
        }
        else {
            passed = true
            p2Total += turnTotal
            updateUI()
        }
    }



    private fun updateUI() {
        if (passed) {
            textViewTurnScore.text = "0"
            textViewTurnTotal.text = "0"
            if (player1Active) {
                textViewP1Total.text = p1Total.toString()
            } else {
                textViewP2Total.text = p2Total.toString()
            }
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
                    textViewPlayer2.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.colorBackgroundActive
                        )
                    )
                    textViewPlayer1.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.colorBackgroundInactive
                        )
                    )
                    player1Active = false
                } else {
                    textViewPlayer1.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.colorBackgroundActive
                        )
                    )
                    textViewPlayer2.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.colorBackgroundInactive
                        )
                    )
                    player1Active = true
                }
            }
        }
    }
/*
    private fun buttonPvPOnClick() {

    }



    private fun buttonPvPOnClick() {

    }

    private fun Reset() {

    }
*/
} //end class