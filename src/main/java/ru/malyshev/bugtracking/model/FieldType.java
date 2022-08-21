package ru.malyshev.bugtracking.model;

import lombok.Data;
import ru.malyshev.bugtracking.model.enums.Type;

import javax.persistence.*;

@Entity
@Data
public class FieldType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Boolean onlyDefault;
}
