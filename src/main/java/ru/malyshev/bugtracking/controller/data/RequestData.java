package ru.malyshev.bugtracking.controller.data;

import lombok.Data;
import ru.malyshev.bugtracking.dto.IssueFieldDto;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

@Data
public class RequestData {
    private List<IssueFieldDto> from;
    private List<IssueFieldDto> to;
    private RelationType relationType;
}