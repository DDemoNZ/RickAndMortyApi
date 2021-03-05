package my.project.rm.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "characters")
public class RMCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;

}
