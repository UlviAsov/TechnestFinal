package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.MovieCRUDDto;
import az.code.finalback.admin.service.Impl.AdminMovieServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/movies")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminMovieController {
    final AdminMovieServiceImpl adminMovieService;

    @GetMapping
    public ResponseEntity<List<MovieCRUDDto>> getAllMovies() {
        List<MovieCRUDDto> movies = adminMovieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody MovieCRUDDto movieCRUDDto) {
        adminMovieService.addMovie(movieCRUDDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieCRUDDto movieCRUDDto) {
        adminMovieService.updateMovie(id, movieCRUDDto);
        return ResponseEntity.ok("Movie updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        adminMovieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
