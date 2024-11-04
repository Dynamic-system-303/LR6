package ru.urfu.lr6.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.lr6.dao.StudentDao;
import ru.urfu.lr6.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return dao.saveStudent(student);
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        return dao.getStudent(id);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) { dao.deleteStudent(id); }
}
