package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.DirectorCRUDDto;
import az.code.finalback.admin.service.AdminDirectorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/directors")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminDirectorController {

    final AdminDirectorService adminDirectorService;

    @GetMapping
    public ResponseEntity<List<DirectorCRUDDto>> getAllDirectors() {
        List<DirectorCRUDDto> directors = adminDirectorService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @PostMapping
    public ResponseEntity<String> addDirector(@RequestBody DirectorCRUDDto directorDto) {
        adminDirectorService.addDirector(directorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Director added successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
        adminDirectorService.deleteDirectorById(id);
        return ResponseEntity.ok("Director deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDirector(@PathVariable Long id, @RequestBody DirectorCRUDDto directorDto) {
        adminDirectorService.updateDirector(id, directorDto);
        return ResponseEntity.ok("Director updated successfully");
    }
}
