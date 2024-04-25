package az.code.finalback.repository;

import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {
    List<Comment> findByMovie(Movie movie);
}
