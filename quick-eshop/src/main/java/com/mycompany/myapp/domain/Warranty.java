package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Warranty.
 */
@Entity
@Table(name = "warranty")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Warranty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "valor")
    private Float valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warranty id(Long id) {
        this.id = id;
        return this;
    }

    public String getTempo() {
        return this.tempo;
    }

    public Warranty tempo(String tempo) {
        this.tempo = tempo;
        return this;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Float getValor() {
        return this.valor;
    }

    public Warranty valor(Float valor) {
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
        if (!(o instanceof Warranty)) {
            return false;
        }
        return id != null && id.equals(((Warranty) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Warranty{" +
            "id=" + getId() +
            ", tempo='" + getTempo() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
