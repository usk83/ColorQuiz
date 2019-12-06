package io.github.usk83.colorquiz

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val question = Question()
    private var score = 0
    private lateinit var scoreLabel: TextView
    private lateinit var colorButtons: Array<Button>
    private lateinit var colorLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreLabel = findViewById(R.id.score_label)
        colorButtons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2)
        )
        colorLabel = findViewById(R.id.color_label)
        scoreLabel.text = score.toString()

        setQuestion()
    }

    private fun setQuestion() {
        val (correctColor, otherColors) = question.getQuestion(colorButtons.size)
        val correctIndex = Random.nextInt(colorButtons.size)
        var colorIndex = 0
        for ((index, button) in colorButtons.withIndex()) {
            if (index == correctIndex) {
                button.setBackgroundColor(correctColor.rgb)
                colorLabel.text = correctColor.toString()
            } else {
                button.setBackgroundColor(otherColors[colorIndex++].rgb)
            }
        }
    }

    fun onClick(view: View) {
        val color = (view.background as? ColorDrawable)?.color ?: 0

        val (message, point) = if (question.checkAnswer(color)) {
            getString(R.string.right_answer_message) to 1
        } else {
            getString(R.string.wrong_answer_message) to -1
        }
        score += point
        scoreLabel.text = score.toString()
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

        setQuestion()
    }

}
