package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("id_user", userId);
        if (meal.isNew()) {
            Number key = insertMeal.executeAndReturnKey(param);
            meal.setId(key.intValue());
        } else {
            int count = namedParameterJdbcTemplate.update("UPDATE meals SET dateTime=:dateTime, description=:description, calories=:calories, " +
                    "id_user=:id_user WHERE id=:id AND id_user=:id_user", param);
            if (count == 0)
                return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("id_user", userId);
        return namedParameterJdbcTemplate.update("DELETE FROM meals WHERE id=:id AND id_user=:id_user", param) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("id_user", userId);
        List<Meal> meals = namedParameterJdbcTemplate.query("SELECT * FROM meals WHERE id=:id AND id_user=:id_user", param, ROW_MAPPER);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE id_user=? ORDER BY datetime DESC ", ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_user", userId)
                .addValue("startDate", startDate)
                .addValue("endDate", endDate);
        return namedParameterJdbcTemplate
                .query("SELECT * FROM meals WHERE id_user=:id_user " +
                        "AND datetime >= :startDate AND datetime <= :endDate ORDER BY datetime DESC", param, ROW_MAPPER);
    }
}
