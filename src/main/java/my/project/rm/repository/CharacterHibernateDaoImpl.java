package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Repository //Can be used instead of JpaRepository
public class CharacterHibernateDaoImpl implements CharacterHibernateDao {

    private final EntityManager entityManager;

    public CharacterHibernateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<RMCharacter> saveAll(List<RMCharacter> characters) {
        characters.forEach(entityManager::persist);
        return new ArrayList<>();
    }

    @Override
    public List<RMCharacter> findAllByNameContaining(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RMCharacter> query = builder.createQuery(RMCharacter.class);

        Root<RMCharacter> from = query.from(RMCharacter.class);

        Predicate nameMatcherPredicate = builder.like(from.get("name"), "%" + name + "%");

        query.where(nameMatcherPredicate);

        TypedQuery<RMCharacter> resultQuery = entityManager.createQuery(query);
        return resultQuery.getResultList();
    }

    @Override
    public RMCharacter findById(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RMCharacter> query = builder.createQuery(RMCharacter.class);

        Root<RMCharacter> from = query.from(RMCharacter.class);

        Predicate randomRowPredicate = builder.equal(from.get("id"), new Random().nextInt((int) (id + 1)));

        CriteriaQuery<RMCharacter> resultQuery = query.select(from).where(randomRowPredicate).distinct(true);

        return entityManager.createQuery(resultQuery).getSingleResult();
    }

    @Override
    public Long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<RMCharacter> from = query.from(RMCharacter.class);

        CriteriaQuery<Long> select = query.select(criteriaBuilder.count(from));

        return entityManager.createQuery(select).getSingleResult();
    }

    @Override
    public List<RMCharacter> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RMCharacter> query = criteriaBuilder.createQuery(RMCharacter.class);
        Root<RMCharacter> from = query.from(RMCharacter.class);

        CriteriaQuery<RMCharacter> resultQuery = query.select(from);

        return entityManager.createQuery(resultQuery).getResultList();
    }
}
