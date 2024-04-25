package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.MovieCRUDDto;

import java.util.List;

public interface AdminMovieService {

    List<MovieCRUDDto> getAllMovies();

    void addMovie(MovieCRUDDto movieCRUDDto);

    void updateMovie(Long movieId, MovieCRUDDto movieCRUDDto);

    void deleteMovie(Long movieId);
}
