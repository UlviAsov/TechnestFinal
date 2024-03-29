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
    private String fullName;
    private String bio;
    private List<MovieDto> movies;
}
