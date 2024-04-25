package az.code.finalback.controller;

import az.code.finalback.dto.LatestMovieResponseDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Movie;
import az.code.finalback.service.Impl.MovieServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MovieController {
    final MovieServiceImpl movieService;
    @GetMapping("/movie")
    public List<MovieDto> getMoviesByName(@RequestParam String name) {
        return movieService.findMoviesByName(name);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable long id) {
        MovieDto movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/latest")
    public List<LatestMovieResponseDto> getLatestMovies() {
        return movieService.getLatestMovies();
    }
    @GetMapping("/genre")
    public List<MovieDto> getMoviesByGenre(@RequestParam String genreName) {
        return movieService.findMoviesByGenre(genreName);
    }
    @GetMapping("/type/{typeName}")
    public ResponseEntity<List<MovieDto>> getMoviesByType(@PathVariable String typeName) {
        List<MovieDto> movies = movieService.getMoviesByType(typeName);
        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }


}
