package ru.malyshev.bugtracking.service;

import ru.malyshev.bugtracking.dto.FieldTypeDto;

import java.util.List;

public interface FieldTypeService {
    List<FieldTypeDto> getAll();
}
