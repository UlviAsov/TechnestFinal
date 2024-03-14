package az.code.finalback.modelMapper;


import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDto toDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie toEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }
}
