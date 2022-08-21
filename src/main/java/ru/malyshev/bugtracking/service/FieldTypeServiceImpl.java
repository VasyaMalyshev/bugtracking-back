package ru.malyshev.bugtracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.bugtracking.dto.FieldTypeDto;
import ru.malyshev.bugtracking.model.FieldType;
import ru.malyshev.bugtracking.repository.FieldTypeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FieldTypeServiceImpl implements FieldTypeService {

    private final FieldTypeRepository fieldTypeRepository;
    private final IssueFieldService issueFieldService;


    @Override
    public List<FieldTypeDto> getAll() {
        List<FieldType> fieldTypes = fieldTypeRepository.findAll();

        List<FieldTypeDto> dtos = fieldTypes.stream()
                .map(f ->  {
                    FieldTypeDto dto = new FieldTypeDto();
                    dto.setId(f.getId());
                    dto.setName(f.getName());
                    dto.setType(f.getType());
                    return dto;
                })
                .collect(Collectors.toList());

        for (FieldTypeDto dto : dtos) {
            dto.setDefaultIssueFields(issueFieldService.findDefault(dto.getId()));;
        }
        return dtos;
    }
}
