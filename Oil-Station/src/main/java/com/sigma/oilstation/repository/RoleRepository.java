package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Role;
import com.sigma.oilstation.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByType(RoleType type);

}
