package ru.malyshev.bugtracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.bugtracking.model.IssueRelation;
import ru.malyshev.bugtracking.repository.IssueRelationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueRelationsServiceImpl implements IssueRelationService {

    private final IssueRelationRepository issueRelationRepository;

    @Override
    public List<IssueRelation> getAllRelations() {
        return issueRelationRepository.findAll();
    }
}
