package az.code.finalback.controller;

import az.code.finalback.dto.ActorDto;
import az.code.finalback.dto.WriterDto;
import az.code.finalback.service.Impl.WriterServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/writers")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class WriterController {
    final WriterServiceImpl writerService;
    @GetMapping("/writer")
    public List<WriterDto> getWriterByName(@RequestParam String writerName) {
       return writerService.getWriterByName(writerName);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WriterDto> getWriterById(@PathVariable long id) {
        WriterDto writerDto = writerService.getWriterById(id);
        if (writerDto != null) {
            return ResponseEntity.ok(writerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
