package az.code.finalback.admin.service.Impl;

import az.code.finalback.admin.dto.GenreCRUDDto;
import az.code.finalback.admin.service.AdminGenreService;
import az.code.finalback.model.Genre;
import az.code.finalback.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminGenreServiceImpl implements AdminGenreService {

    final GenreRepository genreRepository;

    @Override
    public void addGenre(GenreCRUDDto genreCRUDDto) {
        Genre genre = Genre.builder()
                .genreName(genreCRUDDto.getName())
                .build();
        genreRepository.save(genre);
    }

    @Override
    public void deleteGenreById(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Genre not found with id: " + id);
        }
    }

    @Override
    public void updateGenre(Long id, GenreCRUDDto genreCRUDDto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));

        genre.setGenreName(genreCRUDDto.getName());
        genreRepository.save(genre);
    }

    @Override
    public List<GenreCRUDDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private GenreCRUDDto convertToDto(Genre genre) {
        return GenreCRUDDto.builder()
                .id(genre.getId())
                .name(genre.getGenreName())
                .build();
    }
}
