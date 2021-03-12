package my.project.rm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.project.rm.entity.RMCharacter;
import my.project.rm.entity.dto.RMCharacterResponseDto;
import my.project.rm.service.CharacterService;
import my.project.rm.utils.CharacterMapperUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@Api("Character Controller")
@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapperUtil characterMapper;

    public CharacterController(CharacterService characterService,
                               CharacterMapperUtil characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping(value = "/random")
    @ApiOperation("Return information about random character")
    public RMCharacterResponseDto getRandomCharacter() {
        RMCharacter randomCharacter = characterService.getRandomCharacter();
        return characterMapper.mapEntityListToDto(randomCharacter);
    }

    @GetMapping
    @ApiOperation("Return list of all characters whose name match with entered")
    public List<RMCharacterResponseDto> getCharacterByNameMatch(@PathParam("name") String name) {
        return characterMapper.mapEntityListToDto(characterService.getCharactersByNameMatcher(name));
    }

}
