package az.code.finalback.service;

import az.code.finalback.dto.CommentDto;
import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import az.code.finalback.model.User;

import java.util.List;

public interface CommentService{

    Comment addReview(User user, Movie movie, String comment);

    Comment findCommentById(Long commentId);

    Comment editComment(Long commentId, Long userId, String comment);
}
