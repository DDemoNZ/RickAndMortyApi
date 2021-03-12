package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;
import java.util.List;
import java.util.Optional;

public interface CharacterHibernateDao {

    List<RMCharacter> saveAll(List<RMCharacter> characters);

    List<RMCharacter> findAllByNameContaining(String name);

    List<RMCharacter> findAll();

    Optional<RMCharacter> findRMCharacterRandom();

    void save(RMCharacter character);
}
