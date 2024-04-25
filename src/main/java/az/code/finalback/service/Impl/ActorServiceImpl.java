package az.code.finalback.service.Impl;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Actor;
import az.code.finalback.repository.ActorRepository;
import az.code.finalback.service.ActorService;
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
public class ActorServiceImpl implements ActorService {
    final ActorRepository actorRepository;
    @Override
    public List<ActorDto> getActorByName(String fullName) {
        String searchName = fullName.toLowerCase();
        List<Actor> actors = actorRepository.findByFullNameContainingIgnoreCase(searchName);
        return actors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public ActorDto getActorById(long id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);

        if (optionalActor.isEmpty()) {
            return null;
        }

        Actor actor = optionalActor.get();
        return convertToDto(actor);
    }

    private ActorDto convertToDto(Actor actor) {
        ActorDto actorDto = new ActorDto();
        actorDto.setFullName(actor.getFullName());
        actorDto.setBio(actor.getBio());
        actorDto.setHeight(actor.getHeight());
        actorDto.setImgLink(actor.getImgLink());
        actorDto.setVideoLink(actor.getVideoLink());
        actorDto.setBornPlace(actor.getBornPlace());
        actorDto.setBornTime(actor.getBornTime());

        List<MovieDto> movieDtos = actor.getMovies().stream()
                .map(movie -> MovieDto.builder()
                        .id(movie.getId())
                        .storyLine(movie.getStoryLine())
                        .name(movie.getName())
                        .imdbRating(movie.getImdbRating())
                        .movieImg(movie.getMovieImg())
                        .build())
                .collect(Collectors.toList());

        actorDto.setMovies(movieDtos);
        return actorDto;
    }


}
