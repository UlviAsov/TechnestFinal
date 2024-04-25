package az.code.finalback.repository;

import az.code.finalback.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterRepository extends JpaRepository<Writer,Long> {

    List<Writer> findByWriterNameContainingIgnoreCase(String searchQuery);

}
