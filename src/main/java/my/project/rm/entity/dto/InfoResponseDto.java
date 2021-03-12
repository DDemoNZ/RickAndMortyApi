package my.project.rm.entity.dto;

import lombok.Data;

@Data
public class InfoResponseDto {

    private long count;
    private long pages;
    private String next;
    private String prev;

}
