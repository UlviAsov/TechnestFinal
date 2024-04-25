package az.code.finalback.repository;

import az.code.finalback.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByNameContainingIgnoreCase(String searchQuery);


    List<Movie> findByGenres_GenreNameIgnoreCase(String searchGenre);


    List<Movie> findByMovieType_TypeNameIgnoreCase(String typeName);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.genreName IN :genreNames")
    List<Movie> findByGenres(List<String> genreNames);

    List<Movie> findFirst12ByOrderByReleaseTimeDesc();
}
