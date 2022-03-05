package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.domain.Warranty;
import com.mycompany.myapp.domain.Warranty;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface WarrantyFormMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "garantia", source = "garantia")
    WarrantyDTO toWarrantyDTO(Warranty warranty);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tempo", source = "tempo")
    WarrantyDTO toWarrantyDTO(Warranty tempo);
}
