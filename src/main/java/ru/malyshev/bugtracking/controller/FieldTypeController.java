package ru.malyshev.bugtracking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malyshev.bugtracking.dto.FieldTypeDto;
import ru.malyshev.bugtracking.service.FieldTypeService;

import java.util.List;

@RestController
@RequestMapping("/issuetype")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FieldTypeController {
    private final FieldTypeService fieldTypeService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<FieldTypeDto> dtoList = fieldTypeService.getAll();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
