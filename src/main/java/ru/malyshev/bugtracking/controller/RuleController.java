package ru.malyshev.bugtracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.bugtracking.controller.data.RequestData;
import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.RuleDto;
import ru.malyshev.bugtracking.model.FieldType;
import ru.malyshev.bugtracking.model.IssueField;
import ru.malyshev.bugtracking.model.enums.RelationType;
import ru.malyshev.bugtracking.service.RuleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @GetMapping
    public ResponseEntity<?> getAllRules() {
        List<RuleDto> ruleDtos = ruleService.getAllRules();
        return new ResponseEntity<>(ruleDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addRule(@RequestBody RuleDto ruleDto) {
        ruleService.addRule(ruleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkRules(@RequestBody RequestData data) {
        List<IssueField> from = data.getFrom()
                .stream().map(i -> {
                    IssueField issueField = new IssueField();
                    FieldType fieldType = new FieldType();
                    fieldType.setName(i.getFieldTypeDtoName());
                    issueField.setFieldType(fieldType);
                    issueField.setText(i.getText());
                    return issueField;
                }).collect(Collectors.toList());

        List<IssueField> to = data.getTo()
                .stream().map(i -> {
                    IssueField issueField = new IssueField();
                    FieldType fieldType = new FieldType();
                    fieldType.setName(i.getFieldTypeDtoName());
                    issueField.setFieldType(fieldType);
                    issueField.setText(i.getText());
                    return issueField;
                }).collect(Collectors.toList());

        try {
            ruleService.checkRules(from, to, data.getRelationType());
        } catch (RuleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
