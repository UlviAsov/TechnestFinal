package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.DirectorCRUDDto;

import java.util.List;

public interface AdminDirectorService {
    List<DirectorCRUDDto> getAllDirectors();

    void addDirector(DirectorCRUDDto directorDto);

    void deleteDirectorById(Long id);

    void updateDirector(Long id, DirectorCRUDDto directorDto);
}
