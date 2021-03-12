package my.project.rm.utils;

import my.project.rm.entity.dto.ApiResponseDto;
import my.project.rm.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service
public class ApiSyncService {

    @PostConstruct
    public void init() {
        long start = System.currentTimeMillis();
        Logger.getAnonymousLogger().info("Wait while api characters sync...");
        getCharactersList();
        Logger.getAnonymousLogger().info("Api characters sync finished. "
                + (System.currentTimeMillis() - start));
    }

    @Value("${apiRickAndMortyUrl}")
    private String apiRickAndMortyUrl;

    private final RestTemplate restTemplate;
    private final CharacterService characterService;

    public ApiSyncService(CharacterService characterService) {
        this.restTemplate = new RestTemplate();
        this.characterService = characterService;
    }

    @Scheduled(cron = "${syncApiCharactersScheduleCronExpression}")
    public void getCharactersList() {
        String pageUrl = apiRickAndMortyUrl;

        while (pageUrl != null) {
            ResponseEntity<ApiResponseDto> responseEntity = restTemplate
                    .getForEntity(pageUrl, ApiResponseDto.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                characterService.saveAll((responseEntity.getBody().getResults()));
                pageUrl = responseEntity.getBody().getInfo().getNext();
            } else {
                Logger.getLogger("ApiSyncService")
                        .warning("Request to API rejected: " + responseEntity.getStatusCode());
            }
        }
    }
}
