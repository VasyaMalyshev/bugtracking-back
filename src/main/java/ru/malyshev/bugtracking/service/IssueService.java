package ru.malyshev.bugtracking.service;

import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.IssueDto;
import ru.malyshev.bugtracking.model.Issue;
import ru.malyshev.bugtracking.model.IssueField;
import ru.malyshev.bugtracking.model.IssueRelation;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

public interface IssueService {

    void addIssue(IssueDto issueDto) throws RuleException;
    List<IssueDto> getAllIssues();



}
