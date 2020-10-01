package zak.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

/*
class AIPlayer {

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
/*
        else {
            while (p1Total < goalTotal || p2Total < goalTotal) {

                while (p1ActivePlayer) {
                    buttonRoll.setOnClickListener {
                        buttonRollOnCLick()
                    }
                    buttonPass.setOnClickListener {
                        buttonPassOnClick()
                    }
                }
                p1Total += turnTotal
                textViewP1Total.text = p1Total.toString()

                while(!p1ActivePlayer) {

                }
            }
        }
 */
    }

    private fun buttonRollOnCLick() {
        diNumber = (1..6).random()
        if (diNumber != 1) {
            turnTotal += diNumber
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
            }
            else {
                textViewPlayer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundActive))
                textViewPlayer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundInactive))
                player1Active = true
            }
        }
    }



    private fun buttonPassOnClick() {
        textViewTurnScore.text = "0"
        textViewTurnTotal.text = "0"
        if (player1Active) {
            p1Total += turnTotal
            textViewP1Total.text = p1Total.toString()
        }
        else {
            p2Total += turnTotal
            textViewP2Total.text = p2Total.toString()
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