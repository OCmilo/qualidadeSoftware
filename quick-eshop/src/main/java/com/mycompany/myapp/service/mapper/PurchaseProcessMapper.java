package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseProcess} and its DTO {@link PurchaseProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, PurchaseMapper.class })
public interface PurchaseProcessMapper extends EntityMapper<PurchaseProcessDTO, PurchaseProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "purchase", source = "purchase")
    PurchaseProcessDTO toDto(PurchaseProcess s);
}
