package com.example.gameapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class TicTacToeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var board: Array<Array<Button?>>
    private var currentPlayer = "X"
    private var moves = 0
    private var gameEnded = false

    private lateinit var backButton: Button
    private lateinit var winImage: View
    private lateinit var loseImage: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tic_tac_toe_activity)

        initializeViews()
        initializeBoard()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun initializeViews() {
        backButton = findViewById(R.id.backButton)
        winImage = findViewById(R.id.winImage)
        loseImage = findViewById(R.id.loseImage)
    }

    private fun initializeBoard() {
        val ticTacToeBoard = findViewById<GridLayout>(R.id.ticTacToeBoard)

        board = Array(3) { arrayOfNulls<Button>(3) }

        for (i in 0 until 3) {
            val row = ticTacToeBoard.getChildAt(i) as GridLayout

            for (j in 0 until 3) {
                val button = row.getChildAt(j) as Button
                board[i][j] = button
            }
        }
    }

    override fun onClick(view: View) {
        if (!gameEnded) {
            val button = view as Button

            if (button.text.isEmpty()) {
                button.text = currentPlayer
                moves++

                if (checkWin()) {
                    showWinImage()
                    gameEnded = true
                } else if (moves == 9) {
                    showLoseImage()
                    gameEnded = true
                } else {
                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                }
            }
        }
    }

    private fun checkWin(): Boolean {
        val lines = arrayOf(
            arrayOf(board[0][0], board[0][1], board[0][2]),
            arrayOf(board[1][0], board[1][1], board[1][2]),
            arrayOf(board[2][0], board[2][1], board[2][2]),
            arrayOf(board[0][0], board[1][0], board[2][0]),
            arrayOf(board[0][1], board[1][1], board[2][1]),
            arrayOf(board[0][2], board[1][2], board[2][2]),
            arrayOf(board[0][0], board[1][1], board[2][2]),
            arrayOf(board[0][2], board[1][1], board[2][0])
        )

        for (line in lines) {
            if (line[0]?.text == currentPlayer &&
                line[1]?.text == currentPlayer &&
                line[2]?.text == currentPlayer
            ) {
                return true
            }
        }

        return false
    }

    private fun showWinImage() {
        winImage.visibility = View.VISIBLE
        loseImage.visibility = View.GONE
    }

    private fun showLoseImage() {
        winImage.visibility = View.GONE
        loseImage.visibility = View.VISIBLE
    }
}