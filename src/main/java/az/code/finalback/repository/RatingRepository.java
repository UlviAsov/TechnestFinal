package az.code.finalback.repository;

import az.code.finalback.model.Movie;
import az.code.finalback.model.Rating;
import az.code.finalback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository  extends JpaRepository<Rating,Long> {

    List<Rating> findByMovieId(long id);

    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}
