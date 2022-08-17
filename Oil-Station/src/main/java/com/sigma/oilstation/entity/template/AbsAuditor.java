package com.sigma.oilstation.entity.template;

import com.sigma.oilstation.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbsAuditor implements Serializable {

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;

}
