package ru.malyshev.bugtracking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class IssueField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_field_type")
    private FieldType fieldType;

    private String text;

    private boolean isDefault;
}
