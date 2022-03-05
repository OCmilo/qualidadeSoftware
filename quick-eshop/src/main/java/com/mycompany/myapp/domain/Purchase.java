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

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "confirmacao")
    private Boolean confirmacao;

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

    public String getUserAddress() {
        return this.userAddress;
    }

    public Purchase userAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public Boolean getConfirmacao() {
        return this.confirmacao;
    }

    public Purchase confirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
        return this;
    }

    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
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
            ", userAddress='" + getUserAddress() + "'" +
            ", quantity=" + getQuantity() +
            ", confirmacao='" + getConfirmacao() + "'" +
            "}";
    }
}
