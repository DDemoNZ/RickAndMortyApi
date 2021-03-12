package my.project.rm.service;

import my.project.rm.entity.RMCharacter;
import java.util.List;

public interface CharacterService {

    List<RMCharacter> saveAll(List<RMCharacter> characters);

    List<RMCharacter> getCharactersByNameMatcher(String name);

    RMCharacter getRandomCharacter();

    List<RMCharacter> findAll();

    void save(RMCharacter character);
}
