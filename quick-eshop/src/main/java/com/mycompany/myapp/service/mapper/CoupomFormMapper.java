package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CoupomFormDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CoupomForm} and its DTO {@link CoupomFormDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoupomFormMapper extends EntityMapper<CoupomFormDTO, CoupomForm> {}
