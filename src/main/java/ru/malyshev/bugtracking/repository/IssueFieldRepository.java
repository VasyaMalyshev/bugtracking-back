package ru.malyshev.bugtracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.malyshev.bugtracking.model.IssueField;

import java.util.List;

@Repository
public interface IssueFieldRepository extends JpaRepository<IssueField, Long> {

    @Query("SELECT f FROM IssueField f WHERE f.isDefault = true AND f.fieldType.id = :id")
    List<IssueField> findIssueFields(@Param("id") Long id);
}
