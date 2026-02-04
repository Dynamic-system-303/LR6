package ru.urfu.lr6.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.lr6.dao.UserDao;
import ru.urfu.lr6.entity.Role;
import ru.urfu.lr6.entity.User;
import ru.urfu.lr6.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserActionService userActionService;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           UserActionService userActionService) {
        this.userDao = userDao;
        this.userActionService = userActionService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userDao.getAllUser();
        }

    @Override
    @Transactional(readOnly = true)
    public User findByUserName (String username) {
        return userDao.findByUserName (username);
    }

    @Override
    @Transactional(readOnly = true)
    public User register(User user) {
        user.setRole(Role.READ_ONLY);
        User savedUser = userDao.saveUser(user);
        userActionService.log("New user registered: " + user.getUsername());

        return savedUser;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);

        userActionService.log(
                "Deleted user with id: " + id);
    }

    @Override
    @Transactional
    public void changeRole(String username, Role role) {
        User user = userDao.findByUserName(username);
        user.setRole(role);
        userDao.saveUser(user);

        userActionService.log(
                "Changed role for user " + username + " to " + role
        );
    }

}
