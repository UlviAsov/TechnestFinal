package az.code.finalback.controller;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.DirectorDto;
import az.code.finalback.service.Impl.DirectorServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/directors")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DirectorController {
    final DirectorServiceImpl directorService;

    @GetMapping("/director")
    public List<DirectorDto> getDirectorByName(@RequestParam String directorName) {
        return directorService.getDirectorByName(directorName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable long id) {
        DirectorDto directorDto = directorService.getDirectorById(id);
        if (directorDto != null) {
            return ResponseEntity.ok(directorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
