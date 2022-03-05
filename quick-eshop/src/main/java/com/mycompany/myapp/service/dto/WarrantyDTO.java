package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Warranty} entity.
 */
public class WarrantyDTO implements Serializable {

    private Long id;

    private String warrantyDescription;

    private Double warrantyMonths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarrantyDescription() {
        return warrantyDescription;
    }

    public void setWarrantyDescription(String warrantyDescription) {
        this.warrantyDescription = warrantyDescription;
    }

    public Double getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(Double warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WarrantyDTO)) {
            return false;
        }

        WarrantyDTO warrantyDTO = (WarrantyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, warrantyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WarrantyDTO{" +
            "id=" + getId() +
            ", warrantyDescription='" + getWarrantyDescription() + "'" +
            ", warrantyMonths=" + getWarrantyMonths() +
            "}";
    }
}
