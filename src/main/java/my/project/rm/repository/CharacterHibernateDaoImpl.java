package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository //Can be used instead of JpaRepository
public class CharacterHibernateDaoImpl implements CharacterHibernateDao {

    private final EntityManager entityManager;

    public CharacterHibernateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<RMCharacter> saveAll(List<RMCharacter> characters) {
        for (RMCharacter character : characters) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RMCharacter> query = criteriaBuilder.createQuery(RMCharacter.class);
            Root<RMCharacter> from = query.from(RMCharacter.class);
            Predicate predicate = criteriaBuilder.equal(from.get("id"), character.getId());

            query.where(predicate);
            TypedQuery<RMCharacter> managerQuery = entityManager.createQuery(query);
            if (managerQuery.getResultList().isEmpty()) {
                entityManager.persist(character);
            }
        }
        return characters;
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
    public List<RMCharacter> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RMCharacter> query = criteriaBuilder.createQuery(RMCharacter.class);
        Root<RMCharacter> from = query.from(RMCharacter.class);

        CriteriaQuery<RMCharacter> resultQuery = query.select(from);

        return entityManager.createQuery(resultQuery).getResultList();
    }

    @Override
    public Optional<RMCharacter> findRMCharacterRandom() {
        String query = "SELECT * FROM characters ORDER BY RANDOM() LIMIT 1;";
        Query nativeQuery = entityManager.createNativeQuery(query, RMCharacter.class);
        return Optional.of((RMCharacter) nativeQuery.getSingleResult());
    }

    @Override
    public void save(RMCharacter character) {
        entityManager.persist(character);
    }
}
