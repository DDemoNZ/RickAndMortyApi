package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RMCharacterRepository extends JpaRepository<RMCharacter, Long> {

    List<RMCharacter> findAllByNameContaining(String name);

    @Query(value = "SELECT * FROM characters ORDER BY RANDOM() LIMIT 1;", nativeQuery = true)
    Optional<RMCharacter> findRMCharacterRandom();

}
