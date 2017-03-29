package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.CacheMapForMeals;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by user on 29.03.2017.
 */
public class EditMealServlet extends HttpServlet {
    private static final DAO cache = CacheMapForMeals.getInstance();
    private static final Logger LOG = getLogger(EditMealServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("edit meal");
        req.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = (DateTimeFormatter) req.getServletContext().getAttribute("form");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date") + " " + req.getParameter("dateTime"), formatter);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        int id = Integer.parseInt(req.getParameter("id"));
        Meal meal = new Meal(dateTime, description, calories);
        meal.setId(id);
        cache.update(meal);
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}

