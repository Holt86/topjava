package ru.javawebinar.topjava;

import java.io.Serializable;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser implements Serializable{

    private int id;

//    public int id() {
//        return 2;
//    }


    public AuthorizedUser(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}