package az.code.finalback.admin.service.Impl;


import az.code.finalback.admin.dto.WriterCRUDDto;
import az.code.finalback.admin.service.AdminWriterService;
import az.code.finalback.model.Writer;
import az.code.finalback.repository.WriterRepository;
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
public class AdminWriterServiceImpl implements AdminWriterService {
    final WriterRepository writerRepository;
    @Override
    public List<WriterCRUDDto> getAllWriters() {
        List<Writer> writers = writerRepository.findAll();
        return writers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addWriter(WriterCRUDDto writerDto) {
        Writer writer = Writer.builder()
                .writerName(writerDto.getName())
                .bio(writerDto.getBio())
                .height(writerDto.getHeight())
                .bornTime(writerDto.getBornTime())
                .bornPlace(writerDto.getBornPlace())
                .imgLink(writerDto.getImgLink())
                .videoLink(writerDto.getVideoLink())
                .build();
        writerRepository.save(writer);
    }

    @Override
    public void deleteWriterById(Long id) {
        try {
            writerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Writer not found with id: " + id);
        }
    }

    @Override
    public void updateWriter(Long id, WriterCRUDDto writerDto) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Writer not found"));

        writer.setWriterName(writerDto.getName());
        writer.setBio(writerDto.getBio());
        writer.setHeight(writerDto.getHeight());
        writer.setBornTime(writerDto.getBornTime());
        writer.setBornPlace(writerDto.getBornPlace());
        writer.setImgLink(writerDto.getImgLink());
        writer.setVideoLink(writerDto.getVideoLink());

        writerRepository.save(writer);
    }

    private WriterCRUDDto convertToDto(Writer writer) {
        return WriterCRUDDto.builder()
                .id(writer.getId())
                .name(writer.getWriterName())
                .bio(writer.getBio())
                .height(writer.getHeight())
                .bornTime(writer.getBornTime())
                .bornPlace(writer.getBornPlace())
                .imgLink(writer.getImgLink())
                .videoLink(writer.getVideoLink())
                .build();
    }
}
