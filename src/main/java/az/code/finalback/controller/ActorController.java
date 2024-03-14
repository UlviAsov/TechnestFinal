package az.code.finalback.controller;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.service.Impl.ActorServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/actors")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ActorController {
    final ActorServiceImpl actorService;

    @GetMapping("/actor")
    public List<ActorDto> getActorsByName(@RequestParam String fullName) {
        return actorService.findActorsByName(fullName);
    }

}
