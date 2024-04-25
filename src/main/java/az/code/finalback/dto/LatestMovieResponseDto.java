package az.code.finalback.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LatestMovieResponseDto {
    long id;
    String name;
    String movieImg;
    String imdbRating;
}
