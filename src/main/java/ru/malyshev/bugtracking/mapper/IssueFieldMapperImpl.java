package ru.malyshev.bugtracking.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.bugtracking.dto.IssueFieldDto;
import ru.malyshev.bugtracking.model.IssueField;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueFieldMapperImpl implements IssueFieldMapper {

    @Override
    public List<IssueField> convert(List<IssueFieldDto> issueFieldDtos) {
        return null;
    }
}
