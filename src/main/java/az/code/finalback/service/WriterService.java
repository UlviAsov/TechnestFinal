package az.code.finalback.service;

import az.code.finalback.dto.WriterDto;

import java.util.List;

public interface WriterService {
    List<WriterDto> getWriterByName(String writerName);

    WriterDto getWriterById(long id);
}
