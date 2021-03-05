package my.project.rm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.project.rm.entity.RMCharacter;
import my.project.rm.service.CharacterService;
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

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(value = "/random")
    @ApiOperation("Return information about random character")
    public RMCharacter getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @ApiOperation("Return list of all characters whose name match with entered")
    public List<RMCharacter> getCharacterByNameMatch(@PathParam("name") String name) {
        return characterService.getCharactersByNameMatcher(name);
    }

}
