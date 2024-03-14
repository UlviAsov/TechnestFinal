package az.code.finalback.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "writers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String writerName;
    String bio;
}
