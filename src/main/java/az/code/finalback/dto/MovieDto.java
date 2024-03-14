package az.code.finalback.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private String name;
    private String releaseTime;
    private String movieDuration;
    private String imdbRating;
    private String movieType;
    private List<String> directorNames;
    private List<String> writerNames;
    private List<String> genres;
    private List<String> actorNames;
    private List<String> starActors;
}
