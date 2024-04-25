package az.code.finalback.service.Impl;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.DirectorDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Actor;
import az.code.finalback.model.Director;
import az.code.finalback.modelMapper.MovieMapper;
import az.code.finalback.repository.DirectorRepository;
import az.code.finalback.service.DirectorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DirectorServiceImpl implements DirectorService {
    final DirectorRepository directorRepository;

    @Override
    public List<DirectorDto> getDirectorByName(String fullName) {
        String searchName = fullName.toLowerCase();
        List<Director> directors = directorRepository.findByDirectorNameContainingIgnoreCase(searchName);
        return directors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto getDirectorById(long id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);

        if (optionalDirector.isEmpty()) {
            return null;
        }

        Director director = optionalDirector.get();
        return convertToDto(director);
    }

    private DirectorDto convertToDto(Director director) {
        DirectorDto directorDto = new DirectorDto();
        directorDto.setFullName(director.getDirectorName());
        directorDto.setBio(director.getBio());
        directorDto.setHeight(director.getHeight());
        directorDto.setImgLink(director.getImgLink());
        directorDto.setVideoLink(director.getVideoLink());
        directorDto.setBornPlace(director.getBornPlace());
        directorDto.setBornTime(director.getBornTime());

        List<MovieDto> movieDtos = director.getMovies().stream()
                .map(movie -> MovieDto.builder()
                        .id(movie.getId())
                        .storyLine(movie.getStoryLine())
                        .name(movie.getName())
                        .imdbRating(movie.getImdbRating())
                        .movieImg(movie.getMovieImg())
                        .build())
                .collect(Collectors.toList());

        directorDto.setMovies(movieDtos);

        return directorDto;
    }
}
