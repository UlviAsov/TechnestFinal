package az.code.finalback.repository;

import az.code.finalback.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    List<Actor> findByFullNameContainingIgnoreCase(String fullName);

}
