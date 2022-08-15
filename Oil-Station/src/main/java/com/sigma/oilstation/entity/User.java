package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbsEntity {
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}
