package com.recruitment.dto;

import com.recruitment.model.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RepoDTO {
    private String repoName;
    private String ownerLogin;
    private List<Branch> branches;
}
