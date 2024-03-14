package az.code.finalback.service.Impl;

import az.code.finalback.model.Actor;
import az.code.finalback.model.Director;
import az.code.finalback.model.Movie;
import az.code.finalback.model.Writer;
import az.code.finalback.repository.ActorRepository;
import az.code.finalback.repository.DirectorRepository;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.repository.WriterRepository;
import az.code.finalback.service.SearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchServiceImpl implements SearchService {
    final ActorRepository actorRepository;
    final MovieRepository movieRepository;
    final DirectorRepository directorRepository;
    final WriterRepository writerRepository;

    public List<Object> search(String userInput) {
        List<Object> results = new ArrayList<>();

        if (userInput == null || userInput.isEmpty()) {
            return results; 
        }

        String searchQuery = userInput.toLowerCase();
        char firstLetter = searchQuery.charAt(0);

        List<Actor> actors = actorRepository.findByFullNameContainingIgnoreCase(searchQuery);
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(searchQuery);
        List<Director> directors = directorRepository.findByDirectorNameContainingIgnoreCase(searchQuery);
        List<Writer> writers = writerRepository.findByWriterNameContainingIgnoreCase(searchQuery);

        for (Actor actor : actors) {
            if (actor.getFullName().toLowerCase().contains(searchQuery) && actor.getFullName().toLowerCase().charAt(0) == firstLetter) {
                results.add(actor);
            }
        }

        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(searchQuery) && movie.getName().toLowerCase().charAt(0) == firstLetter) {
                results.add(movie);
            }
        }

        for (Director director : directors) {
            if (director.getDirectorName().toLowerCase().contains(searchQuery) && director.getDirectorName().toLowerCase().charAt(0) == firstLetter) {
                results.add(director);
            }
        }

        for (Writer writer : writers) {
            if (writer.getWriterName().toLowerCase().contains(searchQuery) && writer.getWriterName().toLowerCase().charAt(0) == firstLetter) {
                results.add(writer);
            }
        }

        return results;
    }

}
