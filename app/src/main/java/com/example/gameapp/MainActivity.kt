package com.example.gameapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var ticTacToeButton: Button
    private lateinit var guessTheWordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ticTacToeButton = findViewById(R.id.ticTacToeButton)
        guessTheWordButton = findViewById(R.id.guessTheWordButton)

        ticTacToeButton.setOnClickListener {
            navigateToTicTacToe()
        }

        guessTheWordButton.setOnClickListener {
            navigateToGuessTheWord()
        }
    }

    private fun navigateToTicTacToe() {
        val intent = Intent(this, TicTacToeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToGuessTheWord() {
        val intent = Intent(this, GuessTheWordActivity::class.java)
        startActivity(intent)
    }
}
