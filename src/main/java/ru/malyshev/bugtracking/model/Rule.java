package ru.malyshev.bugtracking.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.malyshev.bugtracking.model.enums.RelationType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RuleField> from;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RuleField> to;

    @Enumerated(EnumType.STRING)
    private RelationType relationType;

    private boolean isActive;

    private String errorText;
}