package ru.malyshev.bugtracking.dto;

import lombok.Data;
import ru.malyshev.bugtracking.model.RuleField;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

@Data
public class RuleDto {

    private Long id;

    private String title;

    private List<RuleField> from;

    private List<RuleField> to;

    private RelationType relationType;

    private boolean isActive;

    private String errorText;
}
