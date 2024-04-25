package az.code.finalback.admin.service.Impl;

import az.code.finalback.admin.dto.MovieCRUDDto;
import az.code.finalback.admin.service.AdminMovieService;
import az.code.finalback.model.*;
import az.code.finalback.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminMovieServiceImpl implements AdminMovieService {
    final MovieRepository movieRepository;
    final ActorRepository actorRepository;
    final DirectorRepository directorRepository;
    final WriterRepository writerRepository;
    final GenreRepository genreRepository;
    final TypeRepository typeRepository;
    @Override
    public List<MovieCRUDDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieCRUDDto> movieDtoList = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtoList.add(convertToDto(movie));
        }
        return movieDtoList;
    }

    @Override
    public void addMovie(MovieCRUDDto movieCRUDDto) {
        List<Director> directorList = new ArrayList<>();
        List<Actor> actorList = new ArrayList<>();
        List<Writer> writerList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        List<Actor> starActorsList = new ArrayList<>();
        for (Long directorId : movieCRUDDto.getDirectorIds()) {
            Director director = directorRepository.findById(directorId)
                    .orElseThrow(() -> new IllegalArgumentException("Director not found with id: " + directorId));
            directorList.add(director);
        }

        for (Long writerId : movieCRUDDto.getWriterIds()) {
            Writer writer = writerRepository.findById(writerId)
                    .orElseThrow(() -> new IllegalArgumentException("Writer not found with id: " + writerId));
            writerList.add(writer);
        }

        for (Long actorId : movieCRUDDto.getActorIds()) {
            Actor actor = actorRepository.findById(actorId)
                    .orElseThrow(() -> new IllegalArgumentException("Actor not found with id: " + actorId));
            actorList.add(actor);
        }

        for (Long starActorId : movieCRUDDto.getStarActorIds()) {
            Actor actor = actorRepository.findById(starActorId)
                    .orElseThrow(() -> new IllegalArgumentException("Actor not found with id: " + starActorId));
            starActorsList.add(actor);
        }

        for (Long genreId : movieCRUDDto.getGenreIds()) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with id: " + genreId));
            genreList.add(genre);
        }

        Type type = typeRepository.findById(movieCRUDDto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Type not found with id: " + movieCRUDDto.getTypeId()));

        Movie movie = Movie.builder()
                .name(movieCRUDDto.getName())
                .storyLine(movieCRUDDto.getStoryLine())
                .movieDuration(movieCRUDDto.getMovieDuration())
                .releaseTime(movieCRUDDto.getReleaseTime())
                .movieImg(movieCRUDDto.getMovieImg())
                .movieVideo(movieCRUDDto.getMovieVideo())
                .directors(directorList)
                .actors(actorList)
                .writers(writerList)
                .genres(genreList)
                .starActors(starActorsList)
                .movieType(type)
                .build();

        movieRepository.save(movie);
    }
    @Override
    public void updateMovie(Long movieId, MovieCRUDDto movieCRUDDto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + movieId));

        movie.setName(movieCRUDDto.getName());
        movie.setMovieDuration(movieCRUDDto.getMovieDuration());
        movie.setReleaseTime(movieCRUDDto.getReleaseTime());
        movie.setStoryLine(movieCRUDDto.getStoryLine());
        movie.setMovieImg(movieCRUDDto.getMovieImg());
        movie.setMovieVideo(movieCRUDDto.getMovieVideo());

        // Yönetmenlerin listesini güncelle
        List<Director> directorList = new ArrayList<>();
        for (Long directorId : movieCRUDDto.getDirectorIds()) {
            Director director = directorRepository.findById(directorId)
                    .orElseThrow(() -> new IllegalArgumentException("Director not found with id: " + directorId));
            directorList.add(director);
        }
        movie.setDirectors(directorList);

        // Yazarların listesini güncelle
        List<Writer> writerList = new ArrayList<>();
        for (Long writerId : movieCRUDDto.getWriterIds()) {
            Writer writer = writerRepository.findById(writerId)
                    .orElseThrow(() -> new IllegalArgumentException("Writer not found with id: " + writerId));
            writerList.add(writer);
        }
        movie.setWriters(writerList);

        // Oyuncuların listesini güncelle
        List<Actor> actorList = new ArrayList<>();
        for (Long actorId : movieCRUDDto.getActorIds()) {
            Actor actor = actorRepository.findById(actorId)
                    .orElseThrow(() -> new IllegalArgumentException("Actor not found with id: " + actorId));
            actorList.add(actor);
        }
        movie.setActors(actorList);

        // Yıldız oyuncuların listesini güncelle
        List<Actor> starActorsList = new ArrayList<>();
        for (Long starActorId : movieCRUDDto.getStarActorIds()) {
            Actor starActor = actorRepository.findById(starActorId)
                    .orElseThrow(() -> new IllegalArgumentException("Star Actor not found with id: " + starActorId));
            starActorsList.add(starActor);
        }
        movie.setStarActors(starActorsList);

        // Türlerin listesini güncelle
        List<Genre> genreList = new ArrayList<>();
        for (Long genreId : movieCRUDDto.getGenreIds()) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with id: " + genreId));
            genreList.add(genre);
        }
        movie.setGenres(genreList);

        // Tipi güncelle
        Type type = typeRepository.findById(movieCRUDDto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Type not found with id: " + movieCRUDDto.getTypeId()));
        movie.setMovieType(type);

        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + movieId));
        movieRepository.delete(movie);
    }
    private MovieCRUDDto convertToDto(Movie movie) {
        return MovieCRUDDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .storyLine(movie.getStoryLine())
                .movieDuration(movie.getMovieDuration())
                .releaseTime(movie.getReleaseTime())
                .movieImg(movie.getMovieImg())
                .movieVideo(movie.getMovieVideo())
                .build();
    }
}
