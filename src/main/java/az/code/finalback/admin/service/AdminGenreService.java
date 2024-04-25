package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.GenreCRUDDto;
import az.code.finalback.dto.GenreDto;

import java.util.List;

public interface AdminGenreService {

    void addGenre(GenreCRUDDto genreCRUDDto);

    void deleteGenreById(Long id);

    void updateGenre(Long id, GenreCRUDDto genreCRUDDto);

    List<GenreCRUDDto> getAllGenres();
}
