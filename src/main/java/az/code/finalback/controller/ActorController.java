package az.code.finalback.controller;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.service.Impl.ActorServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
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
    public List<ActorDto> getActorByName(@RequestParam String fullName) {
        return actorService.getActorByName(fullName);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable long id) {
        ActorDto actorDto = actorService.getActorById(id);
        if (actorDto != null) {
            return ResponseEntity.ok(actorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
