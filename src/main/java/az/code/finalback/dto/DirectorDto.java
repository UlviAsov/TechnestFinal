package az.code.finalback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorDto {
    private String fullName;
    private String bio;
    private String born;
    private List<MovieDto> movies;
}
