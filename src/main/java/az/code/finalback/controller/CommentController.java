package az.code.finalback.controller;

import az.code.finalback.dto.CommentDto;
import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import az.code.finalback.model.User;
import az.code.finalback.service.Impl.CommentServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CommentController {
    final CommentServiceImpl reviewService;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody CommentDto commentDto) {
        User user = new User();
        user.setId(commentDto.getUserId());

        Movie movie = new Movie();
        movie.setId(commentDto.getMovieId());

        Comment newReview = reviewService.addReview(user , movie, commentDto.getComment());
        if (newReview != null) {
            return ResponseEntity.ok("Comment added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add review");
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
        Comment comment = reviewService.findCommentById(commentId);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        Long userId = commentDto.getUserId();

        Comment updatedComment = reviewService.editComment(commentId, userId, commentDto.getComment());

        if (updatedComment != null) {
            return ResponseEntity.ok("Comment edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to edit the comment");
        }
    }




}
