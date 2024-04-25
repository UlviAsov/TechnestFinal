package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.WriterCRUDDto;
import az.code.finalback.admin.service.Impl.AdminWriterServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/writers")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminWriterController {
    final AdminWriterServiceImpl adminWriterService;

    @GetMapping
    public ResponseEntity<List<WriterCRUDDto>> getAllWriters() {
        List<WriterCRUDDto> writers = adminWriterService.getAllWriters();
        return ResponseEntity.ok(writers);
    }

    @PostMapping
    public ResponseEntity<String> addWriter(@RequestBody WriterCRUDDto writerDto) {
        adminWriterService.addWriter(writerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Writer added successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWriter(@PathVariable Long id) {
        adminWriterService.deleteWriterById(id);
        return ResponseEntity.ok("Writer deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateWriter(@PathVariable Long id, @RequestBody WriterCRUDDto writerDto) {
        adminWriterService.updateWriter(id, writerDto);
        return ResponseEntity.ok("Writer updated successfully");
    }
}
