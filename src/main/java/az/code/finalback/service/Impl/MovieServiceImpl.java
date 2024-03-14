package az.code.finalback.service.Impl;


import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.*;

import az.code.finalback.modelMapper.MovieMapper;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.repository.TypeRepository;
import az.code.finalback.service.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieServiceImpl implements MovieService {

    final MovieRepository movieRepository;
    final TypeRepository typeRepository;
    final MovieMapper movieMapper;
    final ModelMapper modelMapper;
    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> {
                    MovieDto movieDto = MovieDto.builder()
                            .name(movie.getName())
                            .releaseTime(movie.getReleaseTime())
                            .movieDuration(movie.getMovieDuration())
                            .imdbRating(String.valueOf(movie.getImdbRating()))
                            .movieType(movie.getMovieType().getTypeName())
                            .directorNames(movie.getDirectors().stream()
                                    .map(Director::getDirectorName)
                                    .collect(Collectors.toList()))
                            .writerNames(movie.getWriters().stream()
                                    .map(Writer::getWriterName)
                                    .collect(Collectors.toList()))
                            .genres(movie.getGenres().stream()
                                    .map(Genre::getGenreName)
                                    .collect(Collectors.toList()))
                            .actorNames(movie.getActors().stream()
                                    .map(Actor::getFullName)
                                    .collect(Collectors.toList()))
                            .starActors(movie.getStarActors().stream()
                                    .map(Actor::getFullName)
                                    .collect(Collectors.toList()))
                            .build();
                    return movieDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto getMovieById(long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }
        return convertToDto(movie);
    }


    @Override
    public List<MovieDto> findMoviesByName(String name) {
        String searchName = name.toLowerCase();
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(searchName);
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @Override
    public List<MovieDto> findMoviesByGenre(String genreName) {
        String searchGenre = genreName.toLowerCase();
        List<Movie> movies = movieRepository.findByGenres_GenreNameIgnoreCase(searchGenre);
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<MovieDto> getMoviesByType(String typeName) {
        String searchType = typeName.toLowerCase();
        List<Movie> movies = movieRepository.findByMovieType_TypeNameIgnoreCase(searchType);
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setReleaseTime(movie.getReleaseTime());
        movieDto.setMovieDuration(movie.getMovieDuration());
        movieDto.setImdbRating(String.valueOf(movie.getImdbRating())); // long'u String'e dönüştür
        movieDto.setMovieType(movie.getMovieType().getTypeName()); // Type nesnesinden sadece typeName alınır

        List<String> directorNames = movie.getDirectors().stream().map(Director::getDirectorName).collect(Collectors.toList());
        movieDto.setDirectorNames(directorNames);

        List<String> writerNames = movie.getWriters().stream().map(Writer::getWriterName).collect(Collectors.toList());
        movieDto.setWriterNames(writerNames);

        List<String> genreNames = movie.getGenres().stream().map(Genre::getGenreName).collect(Collectors.toList());
        movieDto.setGenres(genreNames);

        List<String> actorNames = movie.getActors().stream().map(Actor::getFullName).collect(Collectors.toList());
        movieDto.setActorNames(actorNames);

        List<String> starActorNames = movie.getStarActors().stream().map(Actor::getFullName).collect(Collectors.toList());
        movieDto.setStarActors(starActorNames);

        return movieDto;
    }


    private Movie convertToEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }


}
