package az.code.finalback.controller;

import az.code.finalback.dto.RatingDto;
import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import az.code.finalback.model.Rating;
import az.code.finalback.model.User;
import az.code.finalback.service.Impl.RatingServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ratings")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RatingController {
    final RatingServiceImpl ratingService;

    @PostMapping("/add")
    public ResponseEntity<String> addRating(@RequestBody RatingDto ratingDto) {
        User user = new User();
        user.setId(ratingDto.getUserId());

        Movie movie = new Movie();
        movie.setId(ratingDto.getMovieId());

        Rating newRating = ratingService.addRating(user , movie, ratingDto.getRating());
        if (newRating != null) {
            return ResponseEntity.ok("Rating added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add rating");
        }
    }
}
