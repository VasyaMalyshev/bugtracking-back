package ru.malyshev.bugtracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.bugtracking.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
