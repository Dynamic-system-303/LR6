package ru.urfu.lr6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Student;

import java.util.List;

@Slf4j
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("from Student");
        List<Student> allStudents = query.getResultList();
        log.info("getAllStudents{}", allStudents);
        return allStudents;
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(int id) {
        Query query = entityManager.createQuery("delete from Student where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
