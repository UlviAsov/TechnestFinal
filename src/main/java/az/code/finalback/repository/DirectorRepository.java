package az.code.finalback.repository;

import az.code.finalback.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {
    List<Director> findByDirectorNameContainingIgnoreCase(String searchQuery);

}
