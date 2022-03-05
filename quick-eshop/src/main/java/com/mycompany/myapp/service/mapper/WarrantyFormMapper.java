package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WarrantyFormDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WarrantyForm} and its DTO {@link WarrantyFormDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WarrantyFormMapper extends EntityMapper<WarrantyFormDTO, WarrantyForm> {}
