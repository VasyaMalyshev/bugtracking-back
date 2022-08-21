package ru.malyshev.bugtracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.RuleDto;
import ru.malyshev.bugtracking.model.FieldType;
import ru.malyshev.bugtracking.model.IssueField;
import ru.malyshev.bugtracking.model.Rule;
import ru.malyshev.bugtracking.model.RuleField;
import ru.malyshev.bugtracking.model.enums.RelationType;
import ru.malyshev.bugtracking.repository.FieldTypeRepository;
import ru.malyshev.bugtracking.repository.RuleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;
    private final FieldTypeRepository fieldTypeRepository;

    @Override
    public List<RuleDto> getAllRules() {
        List<RuleDto> ruleDtos = ruleRepository.findAll().stream()
                .map(rule -> {
                    RuleDto ruleDto = new RuleDto();
                    ruleDto.setId(rule.getId());
                    ruleDto.setTitle(rule.getTitle());
                    ruleDto.setActive(rule.isActive());
                    ruleDto.setErrorText(rule.getErrorText());
                    ruleDto.setRelationType(rule.getRelationType());
                    ruleDto.setFrom(rule.getFrom());
                    ruleDto.setTo(rule.getTo());
                    return ruleDto;
                }).collect(Collectors.toList());
        return ruleDtos;
    }

    @Override
    public void addRule(RuleDto ruleDto) {
        Rule rule = new Rule();
        rule.setId(ruleDto.getId());
        rule.setTitle(ruleDto.getTitle());
        rule.setRelationType(ruleDto.getRelationType());
        rule.setErrorText(ruleDto.getErrorText());
        rule.setFrom(ruleDto.getFrom().stream().map(ruleField -> {
            ruleField.setFieldType(fieldTypeRepository.findById(ruleField.getFieldType().getId()).get());
            return ruleField;
        }).collect(Collectors.toList()));
        rule.setTo(ruleDto.getTo().stream().map(ruleField -> {
            ruleField.setFieldType(fieldTypeRepository.findById(ruleField.getFieldType().getId()).get());
            return ruleField;
        }).collect(Collectors.toList()));
        rule.setActive(ruleDto.isActive());
        ruleRepository.save(rule);
    }

    @Override
    public void checkRules(List<IssueField> from, List<IssueField> to, RelationType relationType) throws RuleException {
        List<Rule> rules = ruleRepository.findAllByRelationType(relationType);
        for (Rule rule : rules) {
            if (checkFields(rule.getFrom(), from) && checkFields(rule.getTo(), to)) {
                throw new RuleException(rule.getErrorText());
            }
        }
    }

    private boolean checkFields(List<RuleField> ruleFields, List<IssueField> forCheck) {
        List<IssueField> checked = new ArrayList<>();
        for (IssueField fromField : forCheck) {
            for (RuleField ruleField : ruleFields) {
                if (fromField.getFieldType().getName().equals(ruleField.getFieldType().getName()) && fromField.getText().equals(ruleField.getText())) {
                    checked.add(fromField);
                }
            }
        }

        return checked.size() == ruleFields.size() || checked.size() == forCheck.size();
    }
}
