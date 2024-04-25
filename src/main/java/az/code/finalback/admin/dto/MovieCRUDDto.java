package az.code.finalback.admin.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCRUDDto {
    Long id;
    String movieDuration;
    String name;
    String movieImg;
    String movieVideo;
    String releaseTime;
    String storyLine;
    Long typeId;
    List<Long> directorIds;
    List<Long> writerIds;
    List<Long> actorIds;
    List<Long> starActorIds;
    List<Long> genreIds;
}
