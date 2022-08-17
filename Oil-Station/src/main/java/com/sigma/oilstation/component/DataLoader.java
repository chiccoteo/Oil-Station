package com.sigma.oilstation.component;

import com.sigma.oilstation.entity.Role;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.enums.RoleType;
import com.sigma.oilstation.repository.RoleRepository;
import com.sigma.oilstation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepo;

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            List<Role> roles = new LinkedList<>();
            Role admin = new Role(RoleType.ROLE_ADMIN.name(), RoleType.ROLE_ADMIN);
            Role manager = new Role(RoleType.ROLE_MANAGER.name(), RoleType.ROLE_MANAGER);
            Role employee = new Role(RoleType.ROLE_EMPLOYEE.name(), RoleType.ROLE_EMPLOYEE);
            roles.add(admin);
            roles.add(manager);
            roles.add(employee);
            roleRepo.saveAll(roles);


            User adminUser = new User(
                    "AdminUsername",
                    "Admin FIO",
                    passwordEncoder.encode("AdminPassword"),
                    "+998979516170",
                    null,
                    roleRepo.findByType(RoleType.ROLE_ADMIN),
                    false
            );

            userRepo.save(adminUser);
        }
    }
}
