package jamsxz.programming.linguist.view.list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import jamsxz.programming.linguist.R
import jamsxz.programming.linguist.model.Programming

class QuizActivity : AppCompatActivity() {

    private lateinit var programmingLists: List<Programming>

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        programmingLists = Programming.programmingList(applicationContext)

        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        submitButton = findViewById(R.id.submitButton)


        questionTextView.text = getString(R.string.ready_to_take_a_quiz)
        submitButton.text = getString(R.string.startquiz)
        submitButton.setOnClickListener {
            loadQuestion()
        }
    }

    private fun loadQuestion() {
        submitButton.text = getString(R.string.submit)
        submitButton.setOnClickListener {
            checkAnswer()
        }
        val currentProgramming = programmingLists[currentQuestionIndex]
        questionTextView.text = getString(R.string.question_template, currentProgramming.language)
        optionsRadioGroup.removeAllViews()

        val allDescriptions = programmingLists.map { it.description }

        // Make sure correct answer is included in options
        val correctAnswer = currentProgramming.description
        val shuffledOptions = (allDescriptions - correctAnswer).shuffled().take(3) + correctAnswer
        val textSize = resources.getDimensionPixelSize(R.dimen.radio_button_text_size).toFloat()

        for (option in shuffledOptions) {
            val radioButton = RadioButton(this)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.radio_button_margin_bottom)

            radioButton.layoutParams = layoutParams

            radioButton.text = option
            val defaultTextSize = resources.configuration.fontScale

            val scaleFactor = 20f // You can adjust this as needed
            radioButton.textSize = defaultTextSize * scaleFactor
            radioButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            radioButton.setOnCheckedChangeListener { _, isChecked ->
                radioButton.setTextColor(if (isChecked) ContextCompat.getColor(this, R.color.pink) else ContextCompat.getColor(this, R.color.white))
            }

            optionsRadioGroup.addView(radioButton)
        }
    }


    private fun checkAnswer() {
        val selectedOption = optionsRadioGroup.checkedRadioButtonId
        if (selectedOption != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedOption)

            // Check for null before accessing the text
            val selectedAnswer = selectedRadioButton?.text?.toString()

            val correctAnswer = programmingLists[currentQuestionIndex].description
            if (selectedAnswer == correctAnswer) {
                score++
            }

            if (currentQuestionIndex < programmingLists.size - 1) {
                currentQuestionIndex++
                loadQuestion()
            } else {
                showScore()
            }
        }
    }


    private fun showScore() {
        questionTextView.text = getString(R.string.quiz_completed, score, programmingLists.size)
        optionsRadioGroup.removeAllViews()

        submitButton.text = getString(R.string.play_again)
        submitButton.setOnClickListener {
            resetQuiz()
        }
    }

    private fun resetQuiz() {
        // Reset variables and load the first question
        currentQuestionIndex = 0
        score = 0
        loadQuestion()
    }
}
