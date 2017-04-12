package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class MealTestData {

    public static final int MEAL_ONE = 1;
    public static final int MEAL_TWO = 2;
    public static final int MEAL_THREE = 3;

    public static final Meal MEAL1 = new Meal(1, LocalDateTime.of(2017, 04, 11, 10, 02), "Breakfast", 800);
    public static final Meal MEAL2 = new Meal(2, LocalDateTime.of(2017, 04, 11, 14, 20), "Lunch", 1100);
    public static final Meal MEAL3 = new Meal(3, LocalDateTime.of(2017, 04, 11, 21, 20, 26), "Dinner", 1000);
    public static final Meal NEW_MEAL = new Meal( LocalDateTime.of(2017, 04, 12, 22, 34, 17), "New Dinner", 1200);




    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
