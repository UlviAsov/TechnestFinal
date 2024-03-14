package az.code.finalback.service;


import az.code.finalback.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(long id);

    List<MovieDto> findMoviesByName(String name);

    List<MovieDto> findMoviesByGenre(String genre);

    List<MovieDto> getMoviesByType(String typeName);

}
