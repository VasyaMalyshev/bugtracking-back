package ru.malyshev.bugtracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.bugtracking.controller.exception.RuleException;
import ru.malyshev.bugtracking.dto.IssueDto;
import ru.malyshev.bugtracking.model.Issue;
import ru.malyshev.bugtracking.service.IssueService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public ResponseEntity<?> addIssue(@RequestBody IssueDto issue) {
        try {
            issueService.addIssue(issue);
        } catch (RuleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllIssues() {
        List<IssueDto> issueDtoList = issueService.getAllIssues();
        return new ResponseEntity<>(issueDtoList, HttpStatus.OK);
    }
}
