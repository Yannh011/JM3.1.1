package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    User getByName(String name);
}
