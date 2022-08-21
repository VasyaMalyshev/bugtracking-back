package ru.malyshev.bugtracking.service;

import ru.malyshev.bugtracking.model.IssueRelation;

import java.util.List;

public interface IssueRelationService {

    List<IssueRelation> getAllRelations();
}
