package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchGetDTO {
    private UUID id;
    private String name;
    private AddressDTO addressDTO;
    private boolean delete;
}
