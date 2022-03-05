package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Coupon.
 */
@Entity
@Table(name = "coupon")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "coupon_value")
    private Double couponValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coupon id(Long id) {
        this.id = id;
        return this;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public Coupon couponName(String couponName) {
        this.couponName = couponName;
        return this;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Double getCouponValue() {
        return this.couponValue;
    }

    public Coupon couponValue(Double couponValue) {
        this.couponValue = couponValue;
        return this;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coupon)) {
            return false;
        }
        return id != null && id.equals(((Coupon) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Coupon{" +
            "id=" + getId() +
            ", couponName='" + getCouponName() + "'" +
            ", couponValue=" + getCouponValue() +
            "}";
    }
}
