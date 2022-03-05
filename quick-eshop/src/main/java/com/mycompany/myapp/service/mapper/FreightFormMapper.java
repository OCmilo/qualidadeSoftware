package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FreightFormDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FreightForm} and its DTO {@link FreightFormDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FreightFormMapper extends EntityMapper<FreightFormDTO, FreightForm> {}
