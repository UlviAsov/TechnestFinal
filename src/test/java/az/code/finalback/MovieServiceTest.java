package az.code.finalback;

import az.code.finalback.dto.LatestMovieResponseDto;
import az.code.finalback.dto.MovieDto;
import az.code.finalback.model.Comment;
import az.code.finalback.model.Movie;
import az.code.finalback.repository.CommentRepository;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.service.Impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private MovieServiceImpl movieService;
    @Test
    public void testGetMovieById() {
        long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);

        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setComment("Good movie!");
        comments.add(comment1);

        List<Movie> similarMovies = new ArrayList<>();
        Movie similarMovie1 = new Movie();
        similarMovie1.setId(2L);
        similarMovies.add(similarMovie1);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(commentRepository.findByMovie(movie)).thenReturn(comments);
        when(movieRepository.findByGenres(anyList())).thenReturn(similarMovies);

        MovieDto result = movieService.getMovieById(movieId);

        assertNotNull(result);
        assertEquals(movieId, result.getId());

        assertEquals(comments.size(), result.getComments().size());

        assertEquals(similarMovies.size() - 1, result.getSimilarMovies().size());
        assertEquals(similarMovies.get(0).getId(), result.getSimilarMovies().get(0).getId());
    }

    @Test
    public void testFindMoviesByName() {
        String searchName = "Matrix";
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("The Matrix");
        movie1.setImdbRating("8.8");
        movie1.setMovieImg("https://preview.redd.it/52-years-ago-today-the-godfather-released-in-theaters-v0-qkrazg5q6bqc1.jpeg?auto=webp&s=6e0152ba3f287d5157b9726c381dc9c1bd23e900");
        movie1.setMovieVideo("https://www.youtube.com/embed/UaVTIH8mujA?si=CGe3jHJn3K6qM9dP");
        movies.add(movie1);

        when(movieRepository.findByNameContainingIgnoreCase(searchName.toLowerCase())).thenReturn(movies);

        List<MovieDto> result = movieService.findMoviesByName(searchName);

        assertNotNull(result);
        assertEquals(movies.size(), result.size());
        assertEquals(movie1.getName(), result.get(0).getName());
        assertEquals(movie1.getImdbRating(), result.get(0).getImdbRating());
        assertEquals(movie1.getMovieImg(), result.get(0).getMovieImg());
        assertEquals(movie1.getMovieVideo(), result.get(0).getMovieVideo());


    }
    @Test
    public void testGetMoviesByType() {
        String searchType = "action";
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("Movie 1");
        movie1.setImdbRating("8.8");
        movie1.setMovieImg("https://preview.redd.it/52-years-ago-today-the-godfather-released-in-theaters-v0-qkrazg5q6bqc1.jpeg?auto=webp&s=6e0152ba3f287d5157b9726c381dc9c1bd23e900");
        movie1.setMovieVideo("https://www.youtube.com/embed/UaVTIH8mujA?si=CGe3jHJn3K6qM9dP");
        movies.add(movie1);

        when(movieRepository.findByMovieType_TypeNameIgnoreCase(searchType.toLowerCase())).thenReturn(movies);

        List<MovieDto> result = movieService.getMoviesByType(searchType);

        assertNotNull(result);
        assertEquals(movies.size(), result.size());
        assertEquals(movie1.getName(), result.get(0).getName());
        assertEquals(movie1.getImdbRating(), result.get(0).getImdbRating());
        assertEquals(movie1.getMovieImg(), result.get(0).getMovieImg());
        assertEquals(movie1.getMovieVideo(), result.get(0).getMovieVideo());
    }

    @Test
    public void testFindMoviesByGenre() {
        String searchGenre = "comedy";
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("Movie 1");
        movie1.setImdbRating("8.8");
        movie1.setMovieImg("https://preview.redd.it/52-years-ago-today-the-godfather-released-in-theaters-v0-qkrazg5q6bqc1.jpeg?auto=webp&s=6e0152ba3f287d5157b9726c381dc9c1bd23e900");
        movie1.setMovieVideo("https://www.youtube.com/embed/UaVTIH8mujA?si=CGe3jHJn3K6qM9dP");
        movies.add(movie1);

        when(movieRepository.findByGenres_GenreNameIgnoreCase(searchGenre.toLowerCase())).thenReturn(movies);

        List<MovieDto> result = movieService.findMoviesByGenre(searchGenre);

        assertNotNull(result);
        assertEquals(movies.size(), result.size());
        assertEquals(movie1.getName(), result.get(0).getName()); // Örnek film adı kontrolü
        assertEquals(movie1.getImdbRating(), result.get(0).getImdbRating());
        assertEquals(movie1.getMovieImg(), result.get(0).getMovieImg());
        assertEquals(movie1.getMovieVideo(), result.get(0).getMovieVideo());
    }
    @Test
    public void testGetLatestMovies(){
        List<Movie> latestMovies = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Movie movie = new Movie();
            movie.setId((long) (i + 1));
            movie.setName("Movie " + (i + 1));
            movie.setImdbRating("8.8");
            movie.setMovieImg("https://preview.redd.it/52-years-ago-today-the-godfather-released-in-theaters-v0-qkrazg5q6bqc1.jpeg?auto=webp&s=6e0152ba3f287d5157b9726c381dc9c1bd23e900");
            movie.setMovieVideo("https://www.youtube.com/embed/UaVTIH8mujA?si=CGe3jHJn3K6qM9dP");
            latestMovies.add(movie);
        }
        when(movieRepository.findFirst12ByOrderByReleaseTimeDesc()).thenReturn(latestMovies);
        List<LatestMovieResponseDto> movies = movieService.getLatestMovies();

        for (int i = 0; i < latestMovies.size(); i++) {
            LatestMovieResponseDto movie = movies.get(i);
            assertEquals(latestMovies.get(i).getId(), movie.getId());
            assertEquals(latestMovies.get(i).getName(), movie.getName());
            assertEquals(latestMovies.get(i).getMovieImg(), movie.getMovieImg());
            assertEquals(latestMovies.get(i).getImdbRating(), movie.getImdbRating());
        }

        verify(movieRepository, times(1)).findFirst12ByOrderByReleaseTimeDesc();

    }
}
