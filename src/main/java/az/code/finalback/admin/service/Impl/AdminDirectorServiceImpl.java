package az.code.finalback.admin.service.Impl;

import az.code.finalback.admin.dto.DirectorCRUDDto;
import az.code.finalback.admin.service.AdminDirectorService;
import az.code.finalback.model.Director;
import az.code.finalback.repository.DirectorRepository;
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
public class AdminDirectorServiceImpl implements AdminDirectorService {

    final DirectorRepository directorRepository;

    @Override
    public List<DirectorCRUDDto> getAllDirectors() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addDirector(DirectorCRUDDto directorDto) {
        Director director = Director.builder()
                .directorName(directorDto.getName())
                .bio(directorDto.getBio())
                .height(directorDto.getHeight())
                .bornTime(directorDto.getBornTime())
                .bornPlace(directorDto.getBornPlace())
                .imgLink(directorDto.getImgLink())
                .videoLink(directorDto.getVideoLink())
                .build();
        directorRepository.save(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        try {
            directorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Director not found with id: " + id);
        }
    }

    @Override
    public void updateDirector(Long id, DirectorCRUDDto directorDto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Director not found"));

        director.setDirectorName(directorDto.getName());
        director.setBio(directorDto.getBio());
        director.setHeight(directorDto.getHeight());
        director.setBornTime(directorDto.getBornTime());
        director.setBornPlace(directorDto.getBornPlace());
        director.setImgLink(directorDto.getImgLink());
        director.setVideoLink(directorDto.getVideoLink());

        directorRepository.save(director);
    }

    private DirectorCRUDDto convertToDto(Director director) {
        return DirectorCRUDDto.builder()
                .id(director.getId())
                .name(director.getDirectorName())
                .bio(director.getBio())
                .height(director.getHeight())
                .bornTime(director.getBornTime())
                .bornPlace(director.getBornPlace())
                .imgLink(director.getImgLink())
                .videoLink(director.getVideoLink())
                .build();
    }
}
