package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.Role;
import ru.urfu.lr6.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User findByUserName(String username);
    User register(User user);
    void deleteUser(int id);
    void changeRole(String username, Role role);;
}
