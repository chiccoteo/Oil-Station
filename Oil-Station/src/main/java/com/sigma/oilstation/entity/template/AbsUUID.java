package com.sigma.oilstation.entity.template;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbsUUID extends AbsAuditor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
