package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification extends AbsUUID {

    private String text;

    private boolean seen;

}
