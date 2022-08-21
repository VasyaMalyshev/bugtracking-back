package ru.malyshev.bugtracking.mapper;

import ru.malyshev.bugtracking.dto.IssueFieldDto;
import ru.malyshev.bugtracking.model.IssueField;

import java.util.List;

public interface IssueFieldMapper {

    List<IssueField> convert(List<IssueFieldDto> issueFieldDtos);
}
