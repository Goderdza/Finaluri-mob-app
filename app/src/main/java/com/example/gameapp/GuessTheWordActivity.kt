package com.example.gameapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.R
import kotlin.random.Random

class GuessTheWordActivity : AppCompatActivity() {

    private val wordList = listOf(
        Pair("apple", "A common fruit that is often red or green."),
        Pair("banana", "A long, curved fruit with yellow skin."),
        Pair("cat", "A small domesticated carnivorous mammal with soft fur."),
        Pair("dog", "A domesticated carnivorous mammal with four legs and a tail."),
        Pair("elephant", "A large mammal with a long trunk and big ears."),
        Pair("frog", "A small amphibian with long hind legs for leaping.")
    )
    private var currentWordIndex = -1
    private var correctAnswer = ""

    private lateinit var questionText: TextView
    private lateinit var answerInput: EditText
    private lateinit var submitAnswerButton: Button
    private lateinit var resultText: TextView
    private lateinit var correct: TextView
    private lateinit var incorrect: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guess_the_word_activity)

        questionText = findViewById(R.id.questionText)
        answerInput = findViewById(R.id.answerInput)
        submitAnswerButton = findViewById(R.id.submitAnswerButton)
        resultText = findViewById(R.id.resultText)
        correct = findViewById(R.id.correct)
        incorrect = findViewById(R.id.incorrect)
        backButton = findViewById(R.id.backButton)

        generateRandomWord()

        submitAnswerButton.setOnClickListener {
            checkAnswer()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun generateRandomWord() {
        currentWordIndex = Random.nextInt(wordList.size)
        val wordPair = wordList[currentWordIndex]
        correctAnswer = wordPair.first
        val hiddenWord = "_".repeat(correctAnswer.length)
        questionText.text = "Guess the word: $hiddenWord\n\nDescription: ${wordPair.second}"
    }

    private fun checkAnswer() {
        val answer = answerInput.text.toString().trim()

        if (answer.equals(correctAnswer, ignoreCase = true)) {
            resultText.text = "Correct answer!"
            resultText.visibility = View.VISIBLE
            correct.visibility = View.VISIBLE
            incorrect.visibility = View.INVISIBLE
        } else {
            resultText.text = "Incorrect answer!"
            resultText.visibility = View.VISIBLE
            correct.visibility = View.INVISIBLE
            incorrect.visibility = View.VISIBLE
        }
    }
}