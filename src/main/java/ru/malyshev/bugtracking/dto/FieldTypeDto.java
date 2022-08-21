package ru.malyshev.bugtracking.dto;

import lombok.Data;
import ru.malyshev.bugtracking.model.enums.Type;
import ru.malyshev.bugtracking.model.IssueField;

import java.util.List;

@Data
public class FieldTypeDto {

    private Long id;

    private String name;

    private Type type;

    private Boolean onlyDefault;

    private List<IssueField> defaultIssueFields;


}
