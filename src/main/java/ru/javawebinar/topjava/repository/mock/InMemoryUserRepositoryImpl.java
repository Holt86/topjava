package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private AtomicInteger ID = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return users.remove(id) != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) {
            user.setId(ID.incrementAndGet());
        }
        for (Map.Entry<Integer, User> pair : users.entrySet()) {
            if (pair.getValue().getEmail().equals(user.getEmail())) {
                if (pair.getKey() == user.getId()) {
                    users.put(user.getId(), user);
                }
                throw new IllegalArgumentException("email must be unique");
            }
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return new ArrayList<>(users.values())
                .stream()
                .sorted((o1, o2) -> o2.getName().equals(o1.getName())
                        ? o1.getEmail().compareTo(o2.getEmail()) : o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return users.values().stream()
                .filter(o1 -> o1.getEmail().equals(email))
                .findFirst().orElse(null);
    }
}
