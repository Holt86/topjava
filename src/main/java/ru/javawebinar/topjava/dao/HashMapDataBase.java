package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 27.03.2017.
 */
public class HashMapDataBase implements DAO {
    private static HashMapDataBase ourInstance = new HashMapDataBase();

    public static HashMapDataBase getInstance() {
        return ourInstance;
    }

    private HashMapDataBase() {
    }

    @Override
    public <T> List<T> getAllObjects(Class<T> clazz) {
        List mealList = new ArrayList<>();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        return mealList;
    }

    @Override
    public <T> void create(T obj) {

    }

    @Override
    public <T> void update(T obj) {

    }

    @Override
    public <T> void delete(T obj) {

    }
}
