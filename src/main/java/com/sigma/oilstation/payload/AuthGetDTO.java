package com.sigma.oilstation.payload;

import com.sigma.oilstation.enums.RoleType;
import lombok.Data;

@Data
public class AuthGetDTO {

    private String token;

    private RoleType roleType;

    private boolean isBlocked;

}
