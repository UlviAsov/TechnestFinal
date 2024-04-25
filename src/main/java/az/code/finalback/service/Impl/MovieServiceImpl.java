package az.code.finalback.service.Impl;


import az.code.finalback.dto.*;
import az.code.finalback.model.*;

import az.code.finalback.repository.CommentRepository;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.service.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieServiceImpl implements MovieService {

    final MovieRepository movieRepository;
    final CommentRepository commentRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public MovieDto getMovieById(long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }

        MovieDto movieDto = convertToDto(movie);

        List<String> movieGenres = movieDto.getGenres();

        List<Comment> comments = commentRepository.findByMovie(movie);
        int commentCount = comments.size();
        movieDto.setCommentCount(commentCount);

        List<CommentResponseMovieDto> commentDtos = comments.stream()
                .map(comment -> CommentResponseMovieDto.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .username(comment.getUser().getUsername())
                        .userImg(comment.getUser().getUserImg())
                        .userId(comment.getUser().getId())
                        .build())
                .collect(Collectors.toList());

        movieDto.setComments(commentDtos);

        List<Movie> similarMovies = movieRepository.findByGenres(movieGenres);

        List<MovieDto> similarMovieDtos = similarMovies.stream()
                .filter(m -> m.getId() != id)
                .map(this::convertToDtoWithNameAndImgAndRating)
                .collect(Collectors.toList());

        movieDto.setSimilarMovies(similarMovieDtos);

        if (movie.getMovieImg() != null) {
            movieDto.setMovieImg(movie.getMovieImg());
        }
        if (movie.getStoryLine() != null) {
            movieDto.setStoryLine(movie.getStoryLine());
        }
        if (movie.getMovieVideo() != null) {
            movieDto.setMovieVideo(movie.getMovieVideo());
        }

        return movieDto;
    }



    private CommentDto convertReviewToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setMovieId(comment.getMovie().getId());
        commentDto.setComment(comment.getComment());
        return commentDto;
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

    @Override
    public List<LatestMovieResponseDto> getLatestMovies() {
        List<Movie> latestMovies = movieRepository.findFirst12ByOrderByReleaseTimeDesc();
        return latestMovies.stream()
                .map(movie -> LatestMovieResponseDto.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .movieImg(movie.getMovieImg())
                        .imdbRating(movie.getImdbRating())
                        .build())
                .collect(Collectors.toList());
    }


    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setReleaseTime(movie.getReleaseTime());
        movieDto.setMovieDuration(movie.getMovieDuration());
        movieDto.setImdbRating(movie.getImdbRating());
        movieDto.setMovieType(movie.getMovieType().getTypeName());

        //Directors
        List<Director> directors = movie.getDirectors();
        List<MoviePersonDto> directorDtos = new ArrayList<>();
        for (Director director : directors) {
            MoviePersonDto directorDto = new MoviePersonDto();
            directorDto.setId(director.getId());
            directorDto.setName(director.getDirectorName());
            directorDtos.add(directorDto);
        }
        movieDto.setDirectors(directorDtos);

        //Writers
        List<Writer> writers = movie.getWriters();
        List<MoviePersonDto> writerDtos = new ArrayList<>();
        for (Writer writer : writers) {
            MoviePersonDto writerDto = new MoviePersonDto();
            writerDto.setId(writer.getId());
            writerDto.setName(writer.getWriterName());
            writerDtos.add(writerDto);
        }
        movieDto.setWriters(writerDtos);

        List<String> genreNames = movie.getGenres().stream().map(Genre::getGenreName).collect(Collectors.toList());
        movieDto.setGenres(genreNames);

        List<Actor> actors = movie.getActors();

        List<ActorForMovieDto> actorDtos = actors.stream()
                .map(actor -> ActorForMovieDto.builder()
                        .id(actor.getId())
                        .name(actor.getFullName())
                        .actorImg(actor.getImgLink())
                        .build())
                .collect(Collectors.toList());

        movieDto.setActors(actorDtos);

        //Star Actors
        List<Actor> starActors = movie.getStarActors();
        List<MoviePersonDto> starActorsDtos = new ArrayList<>();
        for (Actor starActor : starActors) {
            MoviePersonDto starActorDto = new MoviePersonDto();
            starActorDto.setId(starActor.getId());
            starActorDto.setName(starActor.getFullName());
            starActorsDtos.add(starActorDto);
        }
        movieDto.setStarActors(starActorsDtos);

        List<Movie> similarMovies = movieRepository.findByGenres(genreNames);
        List<MovieDto> similarMovieDtos = similarMovies.stream()
                .filter(m -> m.getId() != movie.getId())
                .map(this::convertToDtoWithNameAndImgAndRating) 
                .collect(Collectors.toList());
        movieDto.setSimilarMovies(similarMovieDtos);
        return movieDto;
    }

    private MovieDto convertToDtoWithNameAndImgAndRating(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setImdbRating(movie.getImdbRating());
        movieDto.setMovieImg(movie.getMovieImg());
        movieDto.setGenres(movie.getGenres().stream().map(Genre::getGenreName).collect(Collectors.toList()));

        List<Director> directors = movie.getDirectors();
        List<MoviePersonDto> directorDtos = new ArrayList<>();
        for (Director director : directors) {
            MoviePersonDto directorDto = new MoviePersonDto();
            directorDto.setId(director.getId());
            directorDto.setName(director.getDirectorName());
            directorDtos.add(directorDto);
        }
        movieDto.setDirectors(directorDtos);

        return movieDto;
    }
    private Movie convertToEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }


}
