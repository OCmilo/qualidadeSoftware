package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Freight} entity.
 */
public class FreightDTO implements Serializable {

    private Long id;

    private String empresa;

    private Float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FreightDTO)) {
            return false;
        }

        FreightDTO freightDTO = (FreightDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, freightDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FreightDTO{" +
            "id=" + getId() +
            ", empresa='" + getEmpresa() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
