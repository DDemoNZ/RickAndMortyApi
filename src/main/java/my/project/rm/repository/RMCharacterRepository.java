package my.project.rm.repository;

import my.project.rm.entity.RMCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RMCharacterRepository extends JpaRepository<RMCharacter, Long> {

    List<RMCharacter> findAllByNameContaining(String name);

    RMCharacter findById(long id);
}
