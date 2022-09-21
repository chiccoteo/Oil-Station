package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findByName(String name);

    boolean existsByName(String debtLimit);

}
