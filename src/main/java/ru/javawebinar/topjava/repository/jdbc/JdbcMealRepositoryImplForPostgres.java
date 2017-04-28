package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import javax.sql.DataSource;

/**
 * Created by user on 27.04.2017.
 */
@Repository
@Profile("postgres")
public class JdbcMealRepositoryImplForPostgres extends JdbcMealRepositoryImpl {

    public JdbcMealRepositoryImplForPostgres(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }


}
