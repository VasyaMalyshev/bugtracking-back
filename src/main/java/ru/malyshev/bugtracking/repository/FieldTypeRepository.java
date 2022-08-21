package ru.malyshev.bugtracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.bugtracking.model.FieldType;

public interface FieldTypeRepository extends JpaRepository<FieldType, Long> {
}
