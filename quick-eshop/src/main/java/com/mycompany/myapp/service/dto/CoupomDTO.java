package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Coupom} entity.
 */
public class CoupomDTO implements Serializable {

    private Long id;

    private String nome;

    private Float desconto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoupomDTO)) {
            return false;
        }

        CoupomDTO coupomDTO = (CoupomDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, coupomDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoupomDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", desconto=" + getDesconto() +
            "}";
    }
}
