package az.code.finalback.admin.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriterCRUDDto {
    Long id;
    String name;
    String bio;
    String height;
    String bornTime;
    String bornPlace;
    String imgLink;
    String videoLink;
}
