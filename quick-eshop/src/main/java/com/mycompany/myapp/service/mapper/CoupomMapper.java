package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CoupomDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Coupom} and its DTO {@link CoupomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoupomMapper extends EntityMapper<CoupomDTO, Coupom> {
    @Named("nome")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    CoupomDTO toDtoNome(Coupom coupom);
}
