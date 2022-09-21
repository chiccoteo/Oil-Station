package com.sigma.oilstation.component;

import com.sigma.oilstation.entity.*;
import com.sigma.oilstation.enums.RoleType;
import com.sigma.oilstation.repository.*;
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
    private final AddressRepository addressRepository;
    private final BranchRepository branchRepository;
    private final FuelRepository fuelRepository;
    private final MeasurementRepository measurementRepository;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            List<Role> roles = new LinkedList<>();
            Role admin = new Role(RoleType.ROLE_ADMIN.name(), RoleType.ROLE_ADMIN);
            Role manager = new Role(RoleType.ROLE_MANAGER.name(), RoleType.ROLE_MANAGER);
            Role employee = new Role(RoleType.ROLE_EMPLOYEE.name(), RoleType.ROLE_EMPLOYEE);
            if (roleRepo.findByType(admin.getType()) == null)
                roles.add(admin);
            if (roleRepo.findByType(manager.getType()) == null)
                roles.add(manager);
            if (roleRepo.findByType(employee.getType()) == null)
                roles.add(employee);
            roleRepo.saveAll(roles);
            Address address = new Address("Andijon","Oltinko'l","Ustozlar","40");
            address = addressRepository.save(address);

            Branch branch = new Branch(address,false);
            branch =branchRepository.save(branch);
            User adminUser = new User(
                    "AdminUsername",
                    "Admin FIO",
                    passwordEncoder.encode("AdminPassword"),
                    "+998979516170",
                    branch,
                    roleRepo.findByType(RoleType.ROLE_ADMIN),
                    false,
                    false
            );
            if (userRepo.findByUsername(adminUser.getUsername()).isEmpty())
                userRepo.save(adminUser);

            Measurement measurement1 = new Measurement("50",false);
            Measurement measurement2 = new Measurement("60",false);
            measurement1 = measurementRepository.save(measurement1);
            measurement2 = measurementRepository.save(measurement2);
            Fuel fuel = new Fuel("A-80",1400,measurement1,measurement2,false);
            fuel =fuelRepository.save(fuel);

        }
    }
}
