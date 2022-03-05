package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.Freight;
import com.mycompany.myapp.domain.Freight;
import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface FreightFormMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "frete", source = "frete")
    FreightDTO toFreightDTO(Freight freight);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "empresa", source = "empresa")
    FreightDTO toFreightDTO(Freight empresa);
}
