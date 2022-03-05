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

    private PurchaseDTO purchase;

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

    public PurchaseDTO getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseDTO purchase) {
        this.purchase = purchase;
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
            ", purchase=" + getPurchase() +
            "}";
    }
}
