package ru.javawebinar.topjava.dao;


import java.util.List;

public interface DAO {

  <T> List<T> getAllObjects(Class<T> clazz);
  <T> void create(T obj);
  <T> void update(T obj);
  <T> void delete(T obj);
  <T> T getObjectById(int id);
}
