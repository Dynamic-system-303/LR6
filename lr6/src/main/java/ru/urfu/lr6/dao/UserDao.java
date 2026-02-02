package ru.urfu.lr6.dao;

import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUser();
    User saveUser (User user);
    User getUser(int id);
    void deleteUser(int id);
}
