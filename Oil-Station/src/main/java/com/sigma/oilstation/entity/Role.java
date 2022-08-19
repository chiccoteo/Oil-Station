package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsLong;
import com.sigma.oilstation.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AbsLong implements GrantedAuthority {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType type;

    @Override
    public String getAuthority() {
        return name;
    }
}
