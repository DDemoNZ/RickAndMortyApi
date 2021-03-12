package my.project.rm.entity.dto;

import lombok.Data;
import my.project.rm.entity.RMCharacter;

import java.util.List;

@Data
public class ApiResponseDto {

    private InfoResponseDto info;
    private List<RMCharacter> results;

}
