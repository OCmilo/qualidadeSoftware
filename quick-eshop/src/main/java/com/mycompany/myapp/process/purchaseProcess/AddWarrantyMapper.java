package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.Purchase;
import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.domain.Warranty;
import com.mycompany.myapp.service.dto.PurchaseDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface AddWarrantyMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "warranty", source = "warranty")
    PurchaseDTO toPurchaseDTO(Purchase purchase);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "warrantyDesc", source = "warrantyDesc")
    WarrantyDTO toWarrantyDTO(Warranty warrantyDesc);
}
