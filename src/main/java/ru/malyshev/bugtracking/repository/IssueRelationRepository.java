package ru.malyshev.bugtracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.bugtracking.model.IssueRelation;

import java.util.List;

@Repository
public interface IssueRelationRepository extends JpaRepository<IssueRelation, Long> {
    List<IssueRelation> findIssueRelationByIssue_Id(Long id);
}
