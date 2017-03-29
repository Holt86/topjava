package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.CacheMapForMeals;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final DAO cacheForMeals = CacheMapForMeals.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals");
        req.setCharacterEncoding("UTF-8");
        List<Meal> mealList = cacheForMeals.getAllObjects(Meal.class);
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, 2000 );
        req.setAttribute("meals", meals);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        LOG.debug("init format for context");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.getServletContext().setAttribute("form", format);


    }
}
