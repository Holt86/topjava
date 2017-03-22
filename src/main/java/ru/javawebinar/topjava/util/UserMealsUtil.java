package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed userMealWithExceed : list) {
            System.out.println(userMealWithExceed);
        }
    }

    public static LocalDate toLocaleDate(UserMeal userMeal) {
        return userMeal.getDateTime().toLocalDate();
    }

    public static boolean isBetween(LocalDateTime localDateTime, LocalTime startTime, LocalTime endTime) {
        LocalTime mealTime = localDateTime.toLocalTime();
        return mealTime.isAfter(startTime) && mealTime.isBefore(endTime);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapCalories = mealList.
                stream().collect(Collectors.toMap(
                userMeal -> (UserMealsUtil.toLocaleDate(userMeal)),
                UserMeal::getCalories,
                (userMeal1, userMeal2) -> userMeal1 + userMeal2));

        List<UserMealWithExceed> mealWithExceedsList = mealList.stream().
                filter(p -> isBetween(p.getDateTime(), startTime, endTime)).
                map(p -> new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(),
                        mapCalories.get(toLocaleDate(p)) > caloriesPerDay)).
                collect(Collectors.toList());

        return mealWithExceedsList;
    }
}
