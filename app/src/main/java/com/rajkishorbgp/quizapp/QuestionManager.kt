package com.rajkishorbgp.quizapp

open class QuestionManager {
    lateinit var question:String
    lateinit var optionA:String
    lateinit var optionB:String
    lateinit var optionC:String
    lateinit var optionD:String
    lateinit var answer:String

    constructor(question:String, optionA:String, optionB:String, optionC:String, optionD:String, answer:String){
        this.question=question
        this.optionA=optionA
        this.optionB=optionB
        this.optionC=optionC
        this.optionD=optionD
        this.answer=answer
    }

}