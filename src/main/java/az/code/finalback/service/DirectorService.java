package az.code.finalback.service;

import az.code.finalback.dto.DirectorDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> getDirectorByName(String directorName);

    DirectorDto getDirectorById(long id);
}
