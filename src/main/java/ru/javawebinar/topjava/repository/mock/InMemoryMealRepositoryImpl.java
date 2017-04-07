package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import ru.javawebinar.topjava.util.DateTimeUtil;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        Meal checkMeal = get(meal.getId(), meal.getUserId());

        if (checkMeal != null) {
            repository.put(meal.getId(), meal);
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = get(id, userId);
        if (meal == null)
            return false;
        return repository.remove(meal.getId()) != null;

    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal != null) {
            if (meal.getUserId() == userId)
                return meal;
        }
        return null;
    }

    /*Return emptyList*/
    @Override
    public Collection<Meal> getAll(int userId, LocalDate start, LocalDate end) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> (DateTimeUtil.isBetween(meal.getDate(), start, end)))
                .sorted(((o1, o2) -> (o1.getDateTime().compareTo(o2.getDateTime()))))
                .collect(Collectors.toList());
    }

    public Map<Integer, Meal> getRepository() {
        return repository;
    }
}

