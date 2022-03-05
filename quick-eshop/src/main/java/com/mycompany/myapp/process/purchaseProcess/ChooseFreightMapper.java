package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.Freight;
import com.mycompany.myapp.domain.Purchase;
import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.dto.PurchaseDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface ChooseFreightMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "withCoupon", source = "withCoupon")
    @Mapping(target = "freight", source = "freight")
    PurchaseDTO toPurchaseDTO(Purchase purchase);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "freighter", source = "freighter")
    FreightDTO toFreightDTO(Freight freighter);
}
