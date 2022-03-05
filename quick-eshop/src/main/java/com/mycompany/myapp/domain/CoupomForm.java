package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CoupomForm.
 */
@Entity
@Table(name = "coupom_form")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CoupomForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "desconto")
    private Float desconto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoupomForm id(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return this.nome;
    }

    public CoupomForm nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDesconto() {
        return this.desconto;
    }

    public CoupomForm desconto(Float desconto) {
        this.desconto = desconto;
        return this;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoupomForm)) {
            return false;
        }
        return id != null && id.equals(((CoupomForm) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoupomForm{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", desconto=" + getDesconto() +
            "}";
    }
}
