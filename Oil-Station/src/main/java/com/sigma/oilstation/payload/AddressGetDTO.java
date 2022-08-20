package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressGetDTO {
    private Long id;

    private String region;

    private String district;

    private String street;

    private String homeNumber;

}
