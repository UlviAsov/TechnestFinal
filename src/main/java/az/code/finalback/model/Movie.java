package az.code.finalback.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String releaseTime;
    String movieDuration;
    String imdbRating;
    String storyLine;
    String movieImg;
    String movieVideo;
    @Transient
    int commentCount;

    @ManyToMany(mappedBy = "movies")
    List<Watchlist> watchlists = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    List<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_writers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id"))
    List<Writer> writers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_star_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    List<Actor> starActors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type movieType;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<Comment> reviews = new ArrayList<>();

}
