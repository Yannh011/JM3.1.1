package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    void delete(long id);
    void update(User user);
    Optional<User> getById(long id);
    User getByName(String name);
}

