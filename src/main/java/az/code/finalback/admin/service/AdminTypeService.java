package az.code.finalback.admin.service;

import az.code.finalback.admin.dto.TypeCRUDDto;

import java.util.List;

public interface AdminTypeService{
    List<TypeCRUDDto> getAllTypes();

    void addType(TypeCRUDDto typeDto);

    void deleteTypeById(Long id);

    void updateType(Long id, TypeCRUDDto typeDto);
}
