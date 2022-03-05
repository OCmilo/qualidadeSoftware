package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.Coupom;
import com.mycompany.myapp.domain.Coupom;
import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.service.dto.CoupomDTO;
import com.mycompany.myapp.service.dto.CoupomDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface CoupomFormMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cupom", source = "cupom")
    CoupomDTO toCoupomDTO(Coupom coupom);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    CoupomDTO toCoupomDTO(Coupom nome);
}
