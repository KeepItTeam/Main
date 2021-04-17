package com.example.studentsimulator;

public class TestQuestion {
    public String question;
    public String rightAnswer;
    public String wrongAnswer;
    TestQuestion(String question,String rightAnswer, String wrongAnswer){
        this.wrongAnswer=wrongAnswer;
        this.question=question;
        this.rightAnswer=rightAnswer;
    }
}
