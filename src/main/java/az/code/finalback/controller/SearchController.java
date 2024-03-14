package az.code.finalback.controller;

import az.code.finalback.service.Impl.SearchServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/search")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SearchController {
    final SearchServiceImpl searchService;

    @GetMapping
    public ResponseEntity<List<Object>> search(@RequestParam String userInput) {
        List<Object> results = searchService.search(userInput);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
