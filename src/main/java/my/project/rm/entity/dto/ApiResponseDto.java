package my.project.rm.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import my.project.rm.entity.RMCharacter;

import java.util.List;

@Data
public class ApiResponseDto {

    @JsonProperty("info")
    private InfoResponseDto infoResponseDto;

    @JsonProperty("results")
    private List<RMCharacter> results;

}
