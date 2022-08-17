package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends AbsUUID {

    private String region;

    private String district;

    private String street;

    private String homeNumber;

}
