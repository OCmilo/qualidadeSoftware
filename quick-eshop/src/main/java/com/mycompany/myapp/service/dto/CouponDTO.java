package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Coupon} entity.
 */
public class CouponDTO implements Serializable {

    private Long id;

    private String couponName;

    private Double couponValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CouponDTO)) {
            return false;
        }

        CouponDTO couponDTO = (CouponDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, couponDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CouponDTO{" +
            "id=" + getId() +
            ", couponName='" + getCouponName() + "'" +
            ", couponValue=" + getCouponValue() +
            "}";
    }
}
