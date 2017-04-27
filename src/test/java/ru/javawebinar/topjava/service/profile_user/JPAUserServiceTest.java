package ru.javawebinar.topjava.service.profile_user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by user on 26.04.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, "jpa"})
public class JPAUserServiceTest extends UserServiceTest {


}
