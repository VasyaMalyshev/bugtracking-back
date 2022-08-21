package ru.malyshev.bugtracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.bugtracking.model.Rule;
import ru.malyshev.bugtracking.model.enums.RelationType;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByRelationType(RelationType relationType);
}
