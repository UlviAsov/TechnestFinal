package az.code.finalback.modelMapper;


import az.code.finalback.dto.MovieDto;
import az.code.finalback.dto.WatchlistMovieDto;
import az.code.finalback.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WatchlistMovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, WatchlistMovieDto.class);
    }

    public Movie convertToEntity(WatchlistMovieDto watchlistMovieDto) {
        return modelMapper.map(watchlistMovieDto, Movie.class);
    }

    public MovieDto toDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie toEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }

    public MovieDto toDtoWithMovieNameAndImdb(Movie movie) {
        if (movie == null) {
            return null;
        }
        return MovieDto.builder()
                .name(movie.getName())
                .imdbRating(movie.getImdbRating())
                .build();
    }


}
