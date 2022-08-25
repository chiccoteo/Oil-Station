package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class UserGetDTO {
    private UUID id;

    private String username;

    private String fio;

    private String password;

    private String phoneNumber;

    private BranchGetDTO branchGetDTO;

    private RoleGetDTO roleGetDTO;

    private boolean deleted;

    private boolean isBlock;
}
