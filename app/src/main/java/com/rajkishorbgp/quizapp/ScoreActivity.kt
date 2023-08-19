package com.rajkishorbgp.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rajkishorbgp.quizapp.databinding.ActivityScoreBinding
import nl.dionsegijn.konfetti.models.Shape.Circle
import nl.dionsegijn.konfetti.models.Shape.Square
import nl.dionsegijn.konfetti.models.Size

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.score.text = "Congratulation!!! your score is ${intent.getIntExtra("SCORE",0)}"
        val score = intent.getIntExtra("SCORE",0)
        val total  =intent.getIntExtra("COUNT",0)
        binding.secondaryScore.text="$score/$total"

        if(score>total/2){
            binding.isWinner.text= "pass"
            binding.viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Square, Circle)
                .addSizes(Size(12, 5f))
                .setPosition(-50f, binding.viewKonfetti.width + 50f, -50f, -50f)
                .streamFor(300, 5000000L)
        }else{
            binding.isWinner.text="If you get more than ${total/2} marks then you pass"
        }
        binding.tryAgain.setOnClickListener {
            startActivity(Intent(this@ScoreActivity,MainActivity::class.java))
            finish()
        }
    }
}