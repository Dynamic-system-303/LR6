package ru.urfu.lr6.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.lr6.dao.DisciplineDao;
import ru.urfu.lr6.entity.Discipline;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineDao dao;

    @Override
    @Transactional
    public List<Discipline> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public Discipline getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    @Transactional
    public Discipline save(Discipline discipline) {
        return dao.save(discipline);
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }
}
