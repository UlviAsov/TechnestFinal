package az.code.finalback.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {
    long id;
    String name;
    String releaseTime;
    String movieDuration;
    String imdbRating;
    String movieType;
    String storyLine;
    String movieImg;
    String movieVideo;
    int commentCount;
    List<String> genres;
    List<MoviePersonDto> directors;
    List<MoviePersonDto> writers;
    List<ActorForMovieDto> actors;
    List<MoviePersonDto> starActors;
    List<MovieDto> similarMovies;
    List<CommentResponseMovieDto> comments;
}
