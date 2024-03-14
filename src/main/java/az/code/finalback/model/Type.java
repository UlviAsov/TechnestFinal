package az.code.finalback.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "movietypes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String typeName;

    @OneToMany(mappedBy = "movieType")
    private List<Movie> movies;
}
