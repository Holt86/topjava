package ru.javawebinar.topjava.web.meal;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.*;

@RestController
@RequestMapping(MealRestController.REST_URL_MEALS)
public class MealRestController extends AbstractMealController {

    static final String REST_URL_MEALS = "/rest/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }


    @GetMapping(value = "/between/{startTime}/{endTime}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable("startTime")
                                                   LocalDateTime startTime, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endTime) {
        LocalDate startDate = startTime.toLocalDate();
        LocalTime startT = startTime.toLocalTime();
        LocalDate endDate = endTime.toLocalDate();
        LocalTime endT = endTime.toLocalTime();
        return super.getBetween(startDate, startT, endDate, endT);
    }


    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getFilter(@RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
                                          @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
      return super.getBetween(startDate != null ? startDate : MIN_DATE, startTime != null ? startTime : LocalTime.MIN,
             endDate != null ? endDate : MAX_DATE, endTime != null ? endTime : LocalTime.MAX);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_MEALS + "{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResourse).body(created);
    }
}