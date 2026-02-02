package ru.urfu.lr6.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.lr6.dao.UserDao;
import ru.urfu.lr6.entity.User;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getAllUser() {
        List<User> users = userDao.getAllUser();
        log.info("Fetched all users: {}", users);
        return users;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        User savedUser = userDao.saveUser(user);
        log.info("Saved user: {}", savedUser);
        return savedUser;
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        User user = userDao.getUser(id);
        log.info("Fetched user by id {}: {}", id, user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
        log.info("Deleted user with id {}", id); }


}
