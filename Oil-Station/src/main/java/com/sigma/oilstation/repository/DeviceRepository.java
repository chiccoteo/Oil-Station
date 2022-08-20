package com.sigma.oilstation.repository;


import com.sigma.oilstation.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

}
