package ru.malyshev.bugtracking.dto;

import lombok.Data;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

@Data
public class IssueRelationDto {

    private Long id;

    private String relationType;

    private List<IssueDto> issueList;
}
