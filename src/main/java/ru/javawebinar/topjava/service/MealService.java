package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.Collection;

public interface MealService {

    Meal save(Meal meal);

    void delete(int id, int userId);

    Meal get(int id, int userId);

//    void update(Meal meal);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getBetweenAll(int userId, LocalDate startDate, LocalDate endDate);
}