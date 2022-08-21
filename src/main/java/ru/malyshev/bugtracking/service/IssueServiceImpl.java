package ru.malyshev.bugtracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.IssueDto;
import ru.malyshev.bugtracking.dto.IssueFieldDto;
import ru.malyshev.bugtracking.dto.IssueRelationDto;
import ru.malyshev.bugtracking.model.*;
import ru.malyshev.bugtracking.model.enums.RelationType;
import ru.malyshev.bugtracking.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final FieldTypeRepository fieldTypeRepository;
    private final IssueRelationRepository issueRelationRepository;

    private final RuleService ruleService;

    @Override
    public void addIssue(IssueDto issueDto) throws RuleException {

        Issue issue = new Issue();

        List<IssueField> issueFieldList = issueDto.getIssueFields().stream()
                .map(dto -> {
                    IssueField issueField = new IssueField();
                    issueField.setId(dto.getId());
                    FieldType fieldType = fieldTypeRepository.findById(dto.getFieldTypeDtoId()).get();
                    issueField.setFieldType(fieldType);
                    issueField.setText(dto.getText());
                    issueField.setDefault(dto.isDefault());
                    return issueField;
                })
                .collect(Collectors.toList());

        issue.setIssueFields(issueFieldList);
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        List<IssueRelation> issueRelationList = issueDto.getIssueRelationDtoList()
                .stream()
                .map(issueRelationDto -> {
                    IssueRelation issueRelation = new IssueRelation();
                    issueRelation.setId(issueRelationDto.getId());
                    issueRelation.setRelationType(RelationType.valueOf(issueRelationDto.getRelationType()));
                    issueRelation.setIssue(issue);
                    issueRelation.setIssueList(issueRelationDto.getIssueList().stream()
                            .map(issueDto1 ->
                                    issueRepository.findById(issueDto1.getId()).get()
                            )
                            .collect(Collectors.toList()));
                    return issueRelation;
                })
                .collect(Collectors.toList());

        for (IssueRelation relation : issueRelationList) {
            for (Issue issue1 : relation.getIssueList()) {
                ruleService.checkRules(issueFieldList, issue1.getIssueFields(), relation.getRelationType());
            }
        }

        issueRepository.save(issue);


        issueRelationRepository.saveAll(issueRelationList);
    }

    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        List<IssueDto> issueDtos = issues.stream()
                .map(issue -> {
                    IssueDto dto = new IssueDto();
                    dto.setId(issue.getId());
                    dto.setTitle(issue.getTitle());
                    dto.setDescription(issue.getDescription());
                    dto.setIssueFields(issue.getIssueFields().stream()
                            .map(issueField -> {
                                IssueFieldDto issueFieldDto = new IssueFieldDto();
                                issueFieldDto.setId(issueField.getId());
                                issueFieldDto.setFieldTypeDtoId(issueField.getFieldType().getId());
                                issueFieldDto.setFieldTypeDtoName(issueField.getFieldType().getName());
                                issueFieldDto.setText(issueField.getText());
                                issueFieldDto.setDefault(issueField.isDefault());
                                return issueFieldDto;
                            })
                            .collect(Collectors.toList()));
                    dto.setIssueRelationDtoList(issueRelationRepository.findIssueRelationByIssue_Id(issue.getId()).stream()
                            .map(issueRelation -> {
                                IssueRelationDto relation = new IssueRelationDto();
                                relation.setId(issueRelation.getId());
                                relation.setRelationType(issueRelation.getRelationType().toString());
                                relation.setIssueList(issueRelation.getIssueList().stream()
                                        .map(issue1 -> {
                                            IssueDto issueDto = new IssueDto();
                                            issueDto.setTitle(issue1.getTitle());
                                            return issueDto;
                                        })
                                        .collect(Collectors.toList()));
                                return relation;
                            })
                            .collect(Collectors.toList()));
                    return dto;

                }).collect(Collectors.toList());
        return issueDtos;
    }


}
