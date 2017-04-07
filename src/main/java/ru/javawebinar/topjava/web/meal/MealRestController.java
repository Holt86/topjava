package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public void save(Meal meal) {
        service.save(meal);
    }

    public void delete(int id, int userId){
        service.delete(id, userId);
    }

    public Meal get(int id, int userId){
        return service.get(id, userId);
    }

    public Collection<Meal> getAll(int userId){
        return service.getAll(userId);
    }


}