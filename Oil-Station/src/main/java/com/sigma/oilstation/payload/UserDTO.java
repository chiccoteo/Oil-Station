package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.UUID;


@Data
public class UserDTO {
    private String username;

    private String fio;

    private String password;

    private String phoneNumber;

    private UUID branchId;

    private Long roleId;

    private boolean deleted;

    private boolean isBlock;
}
