package ru.malyshev.bugtracking.service;

import ru.malyshev.bugtracking.model.IssueField;

import java.util.List;

public interface IssueFieldService {
    List<IssueField> findDefault(Long fieldTypeId);
}
