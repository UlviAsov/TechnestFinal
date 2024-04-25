package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.WriterCRUDDto;

import java.util.List;

public interface AdminWriterService {
    List<WriterCRUDDto> getAllWriters();

    void addWriter(WriterCRUDDto writerDto);

    void deleteWriterById(Long id);

    void updateWriter(Long id, WriterCRUDDto writerDto);
}
