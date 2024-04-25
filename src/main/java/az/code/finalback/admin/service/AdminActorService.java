package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.ActorCRUDDto;

import java.util.List;

public interface AdminActorService {
    List<ActorCRUDDto> getAllActors();

    void addActor(ActorCRUDDto actorDto);

    void deleteActorById(Long id);

    void updateActor(Long id, ActorCRUDDto actorDto);
}
