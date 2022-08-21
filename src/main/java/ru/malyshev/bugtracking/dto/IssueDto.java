package ru.malyshev.bugtracking.dto;

import lombok.Data;

import java.util.List;

@Data
public class IssueDto {

    private Long id;

    private String title;
    private String description;

    private List<IssueFieldDto> issueFields;
    private List<IssueRelationDto> issueRelationDtoList;
}