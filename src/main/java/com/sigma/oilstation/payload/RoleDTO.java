package com.sigma.oilstation.payload;

import com.sigma.oilstation.enums.RoleType;
import lombok.Data;

@Data
public class RoleDTO {
    private String name;
    private RoleType roleType;
}
