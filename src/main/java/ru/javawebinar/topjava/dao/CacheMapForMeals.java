package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class CacheMapForMeals implements DAO {
    private static final Logger LOG = getLogger(CacheMapForMeals.class);
    private static CacheMapForMeals ourInstance = new CacheMapForMeals();
    private static final ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static final AtomicInteger ID = new AtomicInteger();

    public static CacheMapForMeals getInstance() {
        return ourInstance;
    }

    private CacheMapForMeals() {
    }

    @Override
    public <T> List<T> getAllObjects(Class<T> clazz) {

        List<T> allMeals = new ArrayList<T>();
        for (Meal meal : meals.values()) {
            allMeals.add((T) meal);
        }

        return allMeals;
    }

    @Override
    public <T> void create(T obj) {
        Meal meal;
        if (obj instanceof Meal) {
            meal = (Meal) obj;
            meal.setId(ID.getAndIncrement());
            meals.put(meal.getId(), meal);
        } else
            LOG.debug("ERROR");
    }

    @Override
    public <T> void update(T obj) {
        Meal meal = (Meal) obj;
        meals.put(meal.getId(), meal);
    }

    @Override
    public <T> void delete(T obj) {
        Meal meal = (Meal) obj;
        meals.remove(meal.getId());
    }

    @Override
    public <T> T getObjectById(int id) {
        return (T) meals.get(id);
    }
}
