package az.code.finalback.admin.controller;

import az.code.finalback.admin.dto.TypeCRUDDto;
import az.code.finalback.admin.service.Impl.AdminTypeServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/types")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminTypeController {
    final AdminTypeServiceImpl adminTypeService;

    @GetMapping
    public ResponseEntity<List<TypeCRUDDto>> getAllTypes() {
        List<TypeCRUDDto> types = adminTypeService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    @PostMapping
    public ResponseEntity<String> addType(@RequestBody TypeCRUDDto typeDto) {
        adminTypeService.addType(typeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Type added successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteType(@PathVariable Long id) {
        adminTypeService.deleteTypeById(id);
        return ResponseEntity.ok("Type deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateType(@PathVariable Long id, @RequestBody TypeCRUDDto typeDto) {
        adminTypeService.updateType(id, typeDto);
        return ResponseEntity.ok("Type updated successfully");
    }
}
