package az.code.finalback.repository;

import az.code.finalback.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByTypeName(String typeName);
}
