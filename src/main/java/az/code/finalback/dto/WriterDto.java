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
public class WriterDto {
    private String fullName;
    private String bio;
    private List<String> movieNames;
}
