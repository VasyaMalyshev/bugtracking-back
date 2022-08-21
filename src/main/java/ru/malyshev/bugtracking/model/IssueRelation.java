package ru.malyshev.bugtracking.model;

import lombok.Data;
import ru.malyshev.bugtracking.model.enums.RelationType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class IssueRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RelationType relationType;

    @ManyToOne
    @JoinColumn(name = "issue_relationship")
    private Issue issue;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Issue> issueList;
}
