package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

import ru.javawebinar.topjava.MealTestData.*;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 12.04.2017.
 */

@ContextConfiguration(
        {"classpath:spring/spring-app.xml",
                "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator populator;

    @Before
    public void setUp() throws Exception {
        populator.execute();
    }


    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ONE, USER_ID);
        MATCHER.assertEquals(meal, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ONE, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_TWO, USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), Collections.singletonList(MEAL1));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL_TWO, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<Meal> meals = service
                .getBetweenDateTimes(LocalDateTime.of(2017, 04, 11, 10, 02), LocalDateTime.of(2017, 04, 11, 11, 00), USER_ID);
        MATCHER.assertCollectionEquals(meals, Collections.singletonList(MEAL1));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(service.getAll(ADMIN_ID), Collections.singletonList(MEAL3));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(MEAL1);
        meal.setDescription("My Breakfast");
        service.update(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(MEAL_ONE, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal meal = new Meal(MEAL1);
        meal.setDescription("My Breakfast");
        service.update(meal, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal meal = service.save(NEW_MEAL, USER_ID);
        Meal saveMeal = service.get(meal.getId(), USER_ID);
        MATCHER.assertEquals(meal, saveMeal);


    }

}