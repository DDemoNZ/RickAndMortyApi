package my.project.rm.service.impl;

import my.project.rm.entity.RMCharacter;
import my.project.rm.repository.RMCharacterRepository;
import my.project.rm.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final RMCharacterRepository rmCharacterRepository;

    public CharacterServiceImpl(RMCharacterRepository rmCharacterRepository) {
        this.rmCharacterRepository = rmCharacterRepository;
    }

    @Override
    @Transactional
    public List<RMCharacter> saveAll(List<RMCharacter> characters) {
        return rmCharacterRepository.saveAll(characters);
    }

    @Override
    public List<RMCharacter> getCharactersByNameMatcher(String name) {
        return rmCharacterRepository.findAllByNameContaining(name);
    }

    @Override
    public RMCharacter getRandomCharacter() {
        Optional<RMCharacter> rmCharacterRandom = rmCharacterRepository.findRMCharacterRandom();
        if (rmCharacterRandom.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Character not found");
        }
        return rmCharacterRandom.get();
    }

    @Override
    public List<RMCharacter> findAll() {
        return rmCharacterRepository.findAll();
    }

    @Override
    public void save(RMCharacter character) {
        rmCharacterRepository.save(character);
    }
}
