package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;

import java.util.List;

public interface CharacterHibernateDao {

    List<RMCharacter> saveAll(List<RMCharacter> characters);

    List<RMCharacter> findAllByNameContaining(String name);

    RMCharacter findById(long id);

    Long count();

    List<RMCharacter> findAll();

}
