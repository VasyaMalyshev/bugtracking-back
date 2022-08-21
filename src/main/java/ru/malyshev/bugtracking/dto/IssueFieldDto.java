package ru.malyshev.bugtracking.dto;

import lombok.Data;

@Data
public class IssueFieldDto {

    private Long id;

    private Long fieldTypeDtoId;

    private String fieldTypeDtoName;

    private String text;

    private boolean isDefault;
}
