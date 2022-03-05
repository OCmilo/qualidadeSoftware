package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Warranty} and its DTO {@link WarrantyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WarrantyMapper extends EntityMapper<WarrantyDTO, Warranty> {
    @Named("warrantyDesc")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "warrantyDesc", source = "warrantyDesc")
    WarrantyDTO toDtoWarrantyDesc(Warranty warranty);
}
