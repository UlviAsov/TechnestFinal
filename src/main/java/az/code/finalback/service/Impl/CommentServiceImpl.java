package az.code.finalback.service.Impl;


import az.code.finalback.dto.CommentDto;
import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import az.code.finalback.model.User;
import az.code.finalback.repository.CommentRepository;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.repository.UserRepository;
import az.code.finalback.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;
    @Override
    public Comment addReview(User user, Movie movie, String userComment) {
        Comment comment = new Comment();
        comment.setUser(user);
        movie.setCommentCount(movie.getCommentCount() + 1);
        comment.setMovie(movie);
        comment.setComment(userComment);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment editComment(Long commentId, Long userId, String newCommentText) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment != null && existingComment.getUser().getId() == userId) {
            existingComment.setComment(newCommentText);
            return commentRepository.save(existingComment);
        }
        return null;
    }



    private CommentDto convertCommentToDto(Comment comment) {
        return CommentDto.builder()
                .userId(comment.getUser().getId())
                .movieId(comment.getMovie().getId())
                .comment(comment.getComment())
                .build();
    }



}
