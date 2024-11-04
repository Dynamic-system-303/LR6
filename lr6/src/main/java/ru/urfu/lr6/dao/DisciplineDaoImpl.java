package ru.urfu.lr6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Discipline;

import java.util.List;

@Slf4j
@Repository
public class DisciplineDaoImpl implements DisciplineDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAll() {
        var query = entityManager.createQuery("from Discipline");
        List<Discipline> disciplines = query.getResultList();
        log.info("getAllDisciplines{}", disciplines);
        return  disciplines;
    }

    @Override
    public Discipline getOne(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public Discipline save(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public void delete(int id) {
        Query query = entityManager.createQuery("delete from Discipline where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
