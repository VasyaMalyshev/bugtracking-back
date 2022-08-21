package ru.malyshev.bugtracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malyshev.bugtracking.model.IssueField;
import ru.malyshev.bugtracking.repository.IssueFieldRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueFieldServiceImpl implements IssueFieldService {

    private final IssueFieldRepository issueFieldRepository;

    @Override
    public List<IssueField> findDefault(Long fieldTypeId) {
        return issueFieldRepository.findIssueFields(fieldTypeId);
    }
}
