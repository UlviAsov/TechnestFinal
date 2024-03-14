package az.code.finalback.modelMapper;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.model.Actor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    private final ModelMapper modelMapper;

    public ActorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ActorDto toDto(Actor actor) {
        return modelMapper.map(actor, ActorDto.class);
    }

    public Actor toEntity(ActorDto actorDto) {
        return modelMapper.map(actorDto, Actor.class);
    }
}
