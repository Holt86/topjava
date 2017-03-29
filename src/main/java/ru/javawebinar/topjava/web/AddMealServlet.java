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


public class AddMealServlet extends HttpServlet {
    private static final DAO cache = CacheMapForMeals.getInstance();
    private static final Logger LOG = getLogger(AddMealServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("add meal");
        req.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = (DateTimeFormatter) req.getServletContext().getAttribute("form");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date") + " " + req.getParameter("dateTime"), formatter);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        cache.create(new Meal(dateTime, description, calories));
        resp.sendRedirect(req.getContextPath() + "/meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward on edit.jsp");
        req.setCharacterEncoding("UTF-8");
        Meal meal = cache.getObjectById(Integer.parseInt(req.getParameter("id")));
        System.out.println(meal.getDateTime().toString().split(" ")[0]); req.setAttribute("meal", meal);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}
