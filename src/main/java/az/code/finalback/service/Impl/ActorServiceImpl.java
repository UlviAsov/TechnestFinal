package az.code.finalback.service.Impl;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.model.Actor;
import az.code.finalback.modelMapper.ActorMapper;
import az.code.finalback.repository.ActorRepository;
import az.code.finalback.service.ActorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActorServiceImpl implements ActorService {
    final ActorRepository actorRepository;
    final ModelMapper modelMapper;
    final ActorMapper actorMapper;

    @Override
    public List<ActorDto> findActorsByName(String fullName) {
        List<Actor> movies = actorRepository.findByFullNameContainingIgnoreCase(fullName);
        return movies.stream().map(actorMapper::toDto).collect(Collectors.toList());
    }
}
