package ru.urfu.lr6.dao;

import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Student;

import java.util.List;

@Repository
public interface StudentDao {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}
