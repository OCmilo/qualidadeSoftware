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

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "valor")
    private Float valor;

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

    public String getEmpresa() {
        return this.empresa;
    }

    public Freight empresa(String empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Float getValor() {
        return this.valor;
    }

    public Freight valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
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
            ", empresa='" + getEmpresa() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
