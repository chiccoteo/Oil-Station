package com.sigma.oilstation.entity.template;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbsLong extends AbsAuditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
