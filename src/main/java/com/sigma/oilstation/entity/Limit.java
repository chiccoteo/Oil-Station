package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name = "oilLimit")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limit extends AbsLong {

    private String name;

    private Long oilLimit;

}
