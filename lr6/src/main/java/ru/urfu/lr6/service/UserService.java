package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();
    User saveUser(User user);
    User getUser(int id);
    void deleteUser(int id);
}
