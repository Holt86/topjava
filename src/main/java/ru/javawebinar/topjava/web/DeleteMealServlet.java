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

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet {

    private static final DAO cache = CacheMapForMeals.getInstance();
    private static final Logger LOG = getLogger(DeleteMealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("delete meal");
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Meal meal = cache.getObjectById(id);
        cache.delete(meal);
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
