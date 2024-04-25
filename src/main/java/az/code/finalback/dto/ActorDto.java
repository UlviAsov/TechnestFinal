package az.code.finalback.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActorDto {
    String fullName;
    String bio;
    String height;
    String bornTime;
    String bornPlace;
    String imgLink;
    String videoLink;
    List<MovieDto> movies;
}
