package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;


    @Override
    public Meal save(Meal meal) {
        Meal retMeal = repository.save(meal);
        if (retMeal == null)
            throw new IllegalArgumentException("You can't save or change other people's meals");
        return retMeal;
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

//    @Override
//    public void update(Meal meal) {
//
//    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getBetweenAll(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAll(userId, startDate, endDate);
    }
}