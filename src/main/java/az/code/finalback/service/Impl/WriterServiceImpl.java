package az.code.finalback.service.Impl;

import az.code.finalback.dto.MovieDto;
import az.code.finalback.dto.WriterDto;
import az.code.finalback.model.Actor;
import az.code.finalback.model.Writer;
import az.code.finalback.modelMapper.MovieMapper;
import az.code.finalback.repository.WriterRepository;
import az.code.finalback.service.WriterService;
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
public class WriterServiceImpl implements WriterService {
    final WriterRepository writerRepository;
    final MovieMapper movieMapper;

    @Override
    public List<WriterDto> getWriterByName(String fullName) {
        String searchName = fullName.toLowerCase();
        List<Writer> writers = writerRepository.findByWriterNameContainingIgnoreCase(searchName);
        return writers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WriterDto getWriterById(long id) {
        Optional<Writer> optionalWriter = writerRepository.findById(id);

        if (optionalWriter.isEmpty()) {
            return null;
        }

        Writer writer = optionalWriter.get();
        return convertToDto(writer);
    }

    private WriterDto convertToDto(Writer writer) {
        WriterDto writerDto = new WriterDto();
        writerDto.setFullName(writer.getWriterName());
        writerDto.setBio(writer.getBio());
        writerDto.setHeight(writer.getHeight());
        writerDto.setImgLink(writer.getImgLink());
        writerDto.setVideoLink(writer.getVideoLink());
        writerDto.setBornPlace(writer.getBornPlace());
        writerDto.setBornTime(writer.getBornTime());

        List<MovieDto> movieDtos = writer.getMovies().stream()
                .map(movie -> MovieDto.builder()
                        .id(movie.getId())
                        .storyLine(movie.getStoryLine())
                        .name(movie.getName())
                        .imdbRating(movie.getImdbRating())
                        .movieImg(movie.getMovieImg())
                        .build())
                .collect(Collectors.toList());

        writerDto.setMovies(movieDtos);
        return writerDto;
    }


}
