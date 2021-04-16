package com.example.studentsimulator;

public class Student {

    //строковые
    private String name;
    private String city;
    private String university;
    private String gender;

    //числовые
    private int age;
    private int money;
    private int allIncome;
    private int energy;
    private int mood;
    private int stipendIncome;//стипендия
    private int jobIncome;//подработка
    private int otherIncome;//

    //бонусы
    private int luck; //шанс на сокращение количества вариантов на 1
    private int tipNumber;//кол-во шпаргалок
    private int stamina;//сокращение урона энергии в %
    private int cheerfullness;//сокращение урона настроению
    private int discount;//скидка в Пятёрочку

    //инфо о деньгах
    public int getAllIncome() {
        return allIncome;
    }
    public int getMoney() {
        return money;
    }
    public int getStipendIncome() {
        return stipendIncome;
    }
    public int getOtherIncome() {
        return otherIncome;
    }
    public int getJobIncome() {
        return jobIncome;
    }

    public int getEnergy(){return energy;}
    public int getMood(){return mood;}

    Student(){
        allIncome=stipendIncome=jobIncome=otherIncome=money=0;
        mood=energy=85;
    }

}
