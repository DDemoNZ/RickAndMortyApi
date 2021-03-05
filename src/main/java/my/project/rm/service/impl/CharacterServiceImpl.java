package my.project.rm.service.impl;

import my.project.rm.entity.RMCharacter;
import my.project.rm.repository.RMCharacterRepository;
import my.project.rm.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final RMCharacterRepository rmCharacterRepository;

    public CharacterServiceImpl(RMCharacterRepository rmCharacterRepository) {
        this.rmCharacterRepository = rmCharacterRepository;
    }

    @Override
    public List<RMCharacter> saveAll(List<RMCharacter> characters) {
        return rmCharacterRepository.saveAll(characters);
    }

    @Override
    public List<RMCharacter> getCharactersByNameMatcher(String name) {
        return rmCharacterRepository.findAllByNameContaining(name);
    }

    @Override
    public RMCharacter getRandomCharacter() {
        long count = rmCharacterRepository.count();
        return rmCharacterRepository.findById(new Random().nextInt((int) (count + 1)));
    }

    @Override
    public List<RMCharacter> findAll() {
        return rmCharacterRepository.findAll();
    }
}
