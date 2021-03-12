package my.project.rm.utils;

import my.project.rm.entity.RMCharacter;
import my.project.rm.entity.dto.RMCharacterResponseDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterMapperUtil {

    public List<RMCharacterResponseDto> mapEntityListToDto(List<RMCharacter> charactersByNameMatcher) {
        return charactersByNameMatcher.stream()
                .map(this::mapEntityListToDto)
                .collect(Collectors.toList());
    }

    public RMCharacterResponseDto mapEntityListToDto(RMCharacter rmCharacter) {
        RMCharacterResponseDto rmCharacterResponseDto = new RMCharacterResponseDto();
        rmCharacterResponseDto.setGender(rmCharacter.getGender());
        rmCharacterResponseDto.setStatus(rmCharacter.getStatus());
        rmCharacterResponseDto.setName(rmCharacter.getName());
        rmCharacterResponseDto.setImage(rmCharacter.getImage());
        rmCharacterResponseDto.setSpecies(rmCharacter.getSpecies());
        rmCharacterResponseDto.setType(rmCharacter.getType());
        rmCharacterResponseDto.setUrl(rmCharacter.getUrl());
        return rmCharacterResponseDto;
    }
}
