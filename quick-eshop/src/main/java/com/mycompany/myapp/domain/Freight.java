package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Freight.
 */
@Entity
@Table(name = "freight")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Freight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "freighter")
    private String freighter;

    @Column(name = "freight_price")
    private Double freightPrice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Freight id(Long id) {
        this.id = id;
        return this;
    }

    public String getFreighter() {
        return this.freighter;
    }

    public Freight freighter(String freighter) {
        this.freighter = freighter;
        return this;
    }

    public void setFreighter(String freighter) {
        this.freighter = freighter;
    }

    public Double getFreightPrice() {
        return this.freightPrice;
    }

    public Freight freightPrice(Double freightPrice) {
        this.freightPrice = freightPrice;
        return this;
    }

    public void setFreightPrice(Double freightPrice) {
        this.freightPrice = freightPrice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Freight)) {
            return false;
        }
        return id != null && id.equals(((Freight) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Freight{" +
            "id=" + getId() +
            ", freighter='" + getFreighter() + "'" +
            ", freightPrice=" + getFreightPrice() +
            "}";
    }
}
