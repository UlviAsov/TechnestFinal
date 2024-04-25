package az.code.finalback.admin.service.Impl;

import az.code.finalback.admin.dto.ActorCRUDDto;
import az.code.finalback.admin.service.AdminActorService;
import az.code.finalback.model.Actor;
import az.code.finalback.repository.ActorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminActorServiceImpl implements AdminActorService {

    final ActorRepository actorRepository;

    @Override
    public List<ActorCRUDDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addActor(ActorCRUDDto actorDto) {
        Actor actor = Actor.builder()
                .fullName(actorDto.getName())
                .bio(actorDto.getBio())
                .height(actorDto.getHeight())
                .bornTime(actorDto.getBornTime())
                .bornPlace(actorDto.getBornPlace())
                .imgLink(actorDto.getImgLink())
                .videoLink(actorDto.getVideoLink())
                .build();
        actorRepository.save(actor);
    }

    @Override
    public void deleteActorById(Long id) {
        try {
            actorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Actor not found with id: " + id);
        }
    }

    @Override
    public void updateActor(Long id, ActorCRUDDto actorDto) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor not found"));

        actor.setFullName(actorDto.getName());
        actor.setBio(actorDto.getBio());
        actor.setHeight(actorDto.getHeight());
        actor.setBornTime(actorDto.getBornTime());
        actor.setBornPlace(actorDto.getBornPlace());
        actor.setImgLink(actorDto.getImgLink());
        actor.setVideoLink(actorDto.getVideoLink());

        actorRepository.save(actor);
    }

    private ActorCRUDDto convertToDto(Actor actor) {
        return ActorCRUDDto.builder()
                .id(actor.getId())
                .name(actor.getFullName())
                .bio(actor.getBio())
                .height(actor.getHeight())
                .bornTime(actor.getBornTime())
                .bornPlace(actor.getBornPlace())
                .imgLink(actor.getImgLink())
                .videoLink(actor.getVideoLink())
                .build();
    }
}
