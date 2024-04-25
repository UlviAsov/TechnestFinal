package az.code.finalback.service;


import az.code.finalback.dto.LatestMovieResponseDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {
    MovieDto getMovieById(long id);

    List<MovieDto> findMoviesByName(String name);

    List<MovieDto> findMoviesByGenre(String genre);

    List<MovieDto> getMoviesByType(String typeName);

    List<LatestMovieResponseDto> getLatestMovies();
}
