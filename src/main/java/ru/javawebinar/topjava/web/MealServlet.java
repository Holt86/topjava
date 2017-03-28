package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.HashMapDataBase;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;



import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by user on 27.03.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals");
        req.setCharacterEncoding("UTF-8");
        List<Meal> listmeals = HashMapDataBase.getInstance().getAllObjects(Meal.class);
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(listmeals, LocalTime.MIN, LocalTime.MAX, 2000 );
        req.setAttribute("meals", meals);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH-mm-ss");
        this.getServletContext().setAttribute("form", format);


    }
}
