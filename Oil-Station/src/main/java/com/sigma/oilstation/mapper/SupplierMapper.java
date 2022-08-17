package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Supplier;
import com.sigma.oilstation.payload.SupplierGetDTOWithId;
import com.sigma.oilstation.payload.SupplierGetDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierGetDTO toGetDTO(Supplier supplier);

    SupplierGetDTOWithId toGetDTOWithId(Supplier supplier);

    List<SupplierGetDTOWithId> toGetDTOList(List<Supplier> supplierList);

}
