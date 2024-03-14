package az.code.finalback.service;

import az.code.finalback.dto.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> findActorsByName(String fullName);
}
