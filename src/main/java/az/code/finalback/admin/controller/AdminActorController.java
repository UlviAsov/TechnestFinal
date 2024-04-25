package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.ActorCRUDDto;
import az.code.finalback.admin.service.Impl.AdminActorServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/actors")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminActorController {

    final AdminActorServiceImpl adminActorService;

    @GetMapping
    public ResponseEntity<List<ActorCRUDDto>> getAllActors() {
        List<ActorCRUDDto> actors = adminActorService.getAllActors();
        return ResponseEntity.ok(actors);
    }

    @PostMapping
    public ResponseEntity<String> addActor(@RequestBody ActorCRUDDto actorDto) {
        adminActorService.addActor(actorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Actor added successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id) {
        adminActorService.deleteActorById(id);
        return ResponseEntity.ok("Actor deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateActor(@PathVariable Long id, @RequestBody ActorCRUDDto actorDto) {
        adminActorService.updateActor(id, actorDto);
        return ResponseEntity.ok("Actor updated successfully");
    }
}
