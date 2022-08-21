package ru.malyshev.bugtracking.service;

import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.RuleDto;
import ru.malyshev.bugtracking.model.IssueField;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

public interface RuleService {

    List<RuleDto> getAllRules();

    void addRule(RuleDto ruleDto);

    void checkRules(List<IssueField> from, List<IssueField> to, RelationType relationType) throws RuleException;

}
