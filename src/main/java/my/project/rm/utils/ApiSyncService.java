package my.project.rm.utils;

import my.project.rm.entity.RMCharacter;
import my.project.rm.entity.dto.ApiResponseDto;
import my.project.rm.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ApiSyncService {

    @PostConstruct
    public void init() {
        Logger.getAnonymousLogger().info("Wait while api characters sync...");
        getCharactersList();
        Logger.getAnonymousLogger().info("Api characters sync finished");
    }

    @Value("${apiRickAndMortyUrl}")
    private String apiRickAndMortyUrl;

    private final Environment environment;
    private final RestTemplate restTemplate;
    private final CharacterService characterService;

    public ApiSyncService(CharacterService characterService, Environment environment) {
        this.restTemplate = new RestTemplate();
        this.characterService = characterService;
        this.environment = environment;
    }

    @Scheduled(cron = "${syncApiCharactersScheduleCronExpression}")
    public void getCharactersList() {
        while (apiRickAndMortyUrl != null) {
            ResponseEntity<ApiResponseDto> responseEntity = restTemplate.getForEntity(apiRickAndMortyUrl, ApiResponseDto.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                processRMCharacters(responseEntity.getBody().getResults());
                apiRickAndMortyUrl = responseEntity.getBody().getInfoResponseDto().getNext();
            } else {
                Logger.getLogger("ApiSyncService").warning("Request to API rejected: " + responseEntity.getStatusCode());
            }
        }

        apiRickAndMortyUrl = environment.getProperty("apiRickAndMortyUrl");
    }

    private void processRMCharacters(List<RMCharacter> responseCharacters) {
        List<RMCharacter> storedCharacters = characterService.findAll();
        List<RMCharacter> uniqueCharacters = responseCharacters.parallelStream()
                .filter(c -> !storedCharacters.contains(c))
                .collect(Collectors.toList());
        characterService.saveAll(uniqueCharacters);
    }
}
