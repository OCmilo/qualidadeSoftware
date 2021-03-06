package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FreightDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Freight} and its DTO {@link FreightDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FreightMapper extends EntityMapper<FreightDTO, Freight> {
    @Named("freighter")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "freighter", source = "freighter")
    FreightDTO toDtoFreighter(Freight freight);
}
