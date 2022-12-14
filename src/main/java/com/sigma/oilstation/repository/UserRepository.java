package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findUserByDeletedFalseOrderByCreatedDateDesc();

    Page<User> findUsersByDeletedFalse(Pageable pageable);


    List<User> findByBranchId(UUID branch_id);

    List<User> findAllByBranchId(UUID id);

}
