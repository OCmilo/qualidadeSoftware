package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CouponDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Coupon} and its DTO {@link CouponDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CouponMapper extends EntityMapper<CouponDTO, Coupon> {
    @Named("couponName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "couponName", source = "couponName")
    CouponDTO toDtoCouponName(Coupon coupon);
}
