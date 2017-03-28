package ru.javawebinar.topjava.dao;

import java.util.Collection;
import java.util.List;

/**
 * Created by user on 27.03.2017.
 */
public interface DAO {

  <T> Collection<T> getAllObjects(Class<T> clazz);
  <T> void create(T obj);
  <T> void update(T obj);
  <T> void delete(T obj);
}
