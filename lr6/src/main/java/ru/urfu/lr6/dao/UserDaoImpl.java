package ru.urfu.lr6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.User;

import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        Query query = entityManager.createQuery("from User");
        List<User> allUser = query.getResultList();
        log.info("getAllUser{}", allUser);
        return allUser;
    }

    @Override
    public User saveUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findByUserName(String username) {
        try {
            return entityManager.createQuery(
                            "from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
