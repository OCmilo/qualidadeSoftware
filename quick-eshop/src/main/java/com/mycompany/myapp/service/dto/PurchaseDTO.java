package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Purchase} entity.
 */
public class PurchaseDTO implements Serializable {

    private Long id;

    private String userName;

    private String userAddress;

    private Double quantity;

    private Boolean confirmacao;

    private Boolean withCoupon;

    private Boolean withWarranty;

    private CouponDTO coupon;

    private FreightDTO freight;

    private ProductDTO product;

    private WarrantyDTO warranty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public Boolean getWithCoupon() {
        return withCoupon;
    }

    public void setWithCoupon(Boolean withCoupon) {
        this.withCoupon = withCoupon;
    }

    public Boolean getWithWarranty() {
        return withWarranty;
    }

    public void setWithWarranty(Boolean withWarranty) {
        this.withWarranty = withWarranty;
    }

    public CouponDTO getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponDTO coupon) {
        this.coupon = coupon;
    }

    public FreightDTO getFreight() {
        return freight;
    }

    public void setFreight(FreightDTO freight) {
        this.freight = freight;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public WarrantyDTO getWarranty() {
        return warranty;
    }

    public void setWarranty(WarrantyDTO warranty) {
        this.warranty = warranty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseDTO)) {
            return false;
        }

        PurchaseDTO purchaseDTO = (PurchaseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", userAddress='" + getUserAddress() + "'" +
            ", quantity=" + getQuantity() +
            ", confirmacao='" + getConfirmacao() + "'" +
            ", withCoupon='" + getWithCoupon() + "'" +
            ", withWarranty='" + getWithWarranty() + "'" +
            ", coupon=" + getCoupon() +
            ", freight=" + getFreight() +
            ", product=" + getProduct() +
            ", warranty=" + getWarranty() +
            "}";
    }
}
