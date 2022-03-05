package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Purchase.
 */
@Entity
@Table(name = "purchase")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "address")
    private String address;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "confirmation")
    private Boolean confirmation;

    @Column(name = "with_warranty")
    private Boolean withWarranty;

    @Column(name = "with_coupon")
    private Boolean withCoupon;

    @ManyToOne
    private Coupon coupon;

    @ManyToOne
    private Freight freight;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warranty warranty;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return this.userName;
    }

    public Purchase userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public Purchase userEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return this.address;
    }

    public Purchase address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public Purchase quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getConfirmation() {
        return this.confirmation;
    }

    public Purchase confirmation(Boolean confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Boolean getWithWarranty() {
        return this.withWarranty;
    }

    public Purchase withWarranty(Boolean withWarranty) {
        this.withWarranty = withWarranty;
        return this;
    }

    public void setWithWarranty(Boolean withWarranty) {
        this.withWarranty = withWarranty;
    }

    public Boolean getWithCoupon() {
        return this.withCoupon;
    }

    public Purchase withCoupon(Boolean withCoupon) {
        this.withCoupon = withCoupon;
        return this;
    }

    public void setWithCoupon(Boolean withCoupon) {
        this.withCoupon = withCoupon;
    }

    public Coupon getCoupon() {
        return this.coupon;
    }

    public Purchase coupon(Coupon coupon) {
        this.setCoupon(coupon);
        return this;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Freight getFreight() {
        return this.freight;
    }

    public Purchase freight(Freight freight) {
        this.setFreight(freight);
        return this;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }

    public Product getProduct() {
        return this.product;
    }

    public Purchase product(Product product) {
        this.setProduct(product);
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warranty getWarranty() {
        return this.warranty;
    }

    public Purchase warranty(Warranty warranty) {
        this.setWarranty(warranty);
        return this;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        return id != null && id.equals(((Purchase) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Purchase{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", quantity=" + getQuantity() +
            ", confirmation='" + getConfirmation() + "'" +
            ", withWarranty='" + getWithWarranty() + "'" +
            ", withCoupon='" + getWithCoupon() + "'" +
            "}";
    }
}
