package ru.javawebinar.topjava.service.profile_meals;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by user on 26.04.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, "jdbc"})
public class JDBCMealsServiceTest extends MealServiceTest {
}
