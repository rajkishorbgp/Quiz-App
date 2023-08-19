package com.rajkishorbgp.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.rajkishorbgp.quizapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<QuestionManager>
    private lateinit var languageManager: LanguageManager
    private var count = 0
    private var score = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        languageManager = LanguageManager(this)

        list = ArrayList()

        // Question
        list.add(QuestionManager("Which of the following is not a valid relational operator ?",
            "*", "==", ">=", "<=", "*"))
        list.add(QuestionManager("Which is an incorrect variable name ?",
            "Id_No", "ID_NO", "ID_NO", "Id No", "Id No"))
        list.add(QuestionManager("scanf() is a predefined function in______header file.",
            "stdlib.h", "ctype.h", "stdio.h", " stdarg.h", "stdio.h"))
        list.add(QuestionManager("C Programming Language is often called as :",
            "High Level Language", "Middle Level Language", "Low Level Language", "None of these", "Middle Level Language"))
        list.add(QuestionManager("Who is the father of C language?",
            "Steve Jobs", "James Gosling", "Dennis Ritchie", "Rasmus Lerdorf", "Dennis Ritchie"))
        list.add(QuestionManager("Which of the following is not a valid C variable name ?",
            "int number;", "float rate;", "int variable_count;","int $ main;", "int $ main;"))
        list.add(QuestionManager("All keywords in C are in ____________",
            "LowerCase letters", "UpperCase letters", "UpperCase letters", "None of the mentioned", "LowerCase letters"))
        list.add(QuestionManager("What is an example of iteration in C ?",
            "for", "while", "do-while", "all of the mentioned", "all of the mentioned"))
        list.add(QuestionManager("The C-preprocessors are specified with _________ symbol.",
            "#", "$", "/", "&", "#"))
        list.add(QuestionManager("What is the sizeof(char) in a 32-bit C compiler ?",
            "1 bit", "2 bits", "1 Byte", "2 Bytes", "1 Byte"))


        binding.totalQuestion.text = "${list.size}"

        if (list.isNotEmpty()) {
            if (count == 0) {
                binding.question.text = list[0].question
                binding.optionA.text = list[0].optionA
                binding.optionB.text = list[0].optionB
                binding.optionC.text = list[0].optionC
                binding.optionD.text = list[0].optionD
                binding.questionCount.text = "${this.count + 1}."
            }
        }

        binding.optionA.setOnClickListener{
            binding.optionA.text.toString().let { answer ->
                countScore(answer)
            }
        }

        binding.optionB.setOnClickListener{
            binding.optionB.text.toString().let { answer ->
                countScore(answer)
            }
        }

        binding.optionC.setOnClickListener{
            binding.optionC.text.toString().let { answer ->
                countScore(answer)
            }
        }

        binding.optionD.setOnClickListener{
            binding.optionD.text.toString().let { answer ->
                countScore(answer)
            }
        }

        binding.next.setOnClickListener {
            count++
            if (count >= list.size) {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("COUNT", count)
                startActivity(intent)
                finish()
            } else {
                // To clear the selection:
                binding.optionsRadioGroup.clearCheck()
                binding.questionCount.text = "${this.count + 1}."
                binding.question.text = list[count].question
                binding.optionA.text = list[count].optionA
                binding.optionB.text = list[count].optionB
                binding.optionC.text = list[count].optionC
                binding.optionD.text = list[count].optionD
            }
        }
    }

    private fun countScore(i: String) {
        if (count < list.size) {
            if (list[count].answer == i) {
                score++
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.setting) {
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}