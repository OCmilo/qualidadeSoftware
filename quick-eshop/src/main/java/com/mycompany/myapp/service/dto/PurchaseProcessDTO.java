package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PurchaseProcess} entity.
 */
public class PurchaseProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseProcessDTO)) {
            return false;
        }

        PurchaseProcessDTO purchaseProcessDTO = (PurchaseProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", product=" + getProduct() +
            "}";
    }
}
