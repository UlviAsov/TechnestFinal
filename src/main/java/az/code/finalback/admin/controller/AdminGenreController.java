package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.GenreCRUDDto;
import az.code.finalback.admin.service.Impl.AdminGenreServiceImpl;
import az.code.finalback.dto.GenreDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/genres")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminGenreController {
    final AdminGenreServiceImpl adminGenreService;


    @GetMapping
    public ResponseEntity<List<GenreCRUDDto>> getAllGenres() {
        List<GenreCRUDDto> genres = adminGenreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @PostMapping
    public ResponseEntity<String> addGenre(@RequestBody GenreCRUDDto genreCRUDDto) {
        adminGenreService.addGenre(genreCRUDDto);
        return ResponseEntity.ok("Genre added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
        adminGenreService.deleteGenreById(id);
        return ResponseEntity.ok("Genre deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable Long id, @RequestBody GenreCRUDDto genreCRUDDto) {
        adminGenreService.updateGenre(id, genreCRUDDto);
        return ResponseEntity.ok("Genre updated successfully");
    }



}
