package az.code.finalback.admin.service.Impl;

import az.code.finalback.admin.dto.TypeCRUDDto;
import az.code.finalback.admin.service.AdminTypeService;
import az.code.finalback.model.Type;
import az.code.finalback.repository.TypeRepository;
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
public class AdminTypeServiceImpl implements AdminTypeService {
    final TypeRepository typeRepository;
    @Override
    public List<TypeCRUDDto> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addType(TypeCRUDDto typeDto) {
        Type type = Type.builder()
                .typeName(typeDto.getName())
                .build();
        typeRepository.save(type);
    }

    @Override
    public void deleteTypeById(Long id) {
        try {
            typeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Type not found with id: " + id);
        }
    }

    @Override
    public void updateType(Long id, TypeCRUDDto typeDto) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));

        type.setTypeName(typeDto.getName());

        typeRepository.save(type);
    }

    private TypeCRUDDto convertToDto(Type type) {
        return TypeCRUDDto.builder()
                .id(type.getId())
                .name(type.getTypeName())
                .build();
    }
}
