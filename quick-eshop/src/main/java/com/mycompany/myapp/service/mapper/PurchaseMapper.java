package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PurchaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Purchase} and its DTO {@link PurchaseDTO}.
 */
@Mapper(componentModel = "spring", uses = { CouponMapper.class, FreightMapper.class, ProductMapper.class, WarrantyMapper.class })
public interface PurchaseMapper extends EntityMapper<PurchaseDTO, Purchase> {
    @Mapping(target = "coupon", source = "coupon", qualifiedByName = "couponName")
    @Mapping(target = "freight", source = "freight", qualifiedByName = "freighter")
    @Mapping(target = "product", source = "product", qualifiedByName = "productName")
    @Mapping(target = "warranty", source = "warranty", qualifiedByName = "warrantyDesc")
    PurchaseDTO toDto(Purchase s);
}
