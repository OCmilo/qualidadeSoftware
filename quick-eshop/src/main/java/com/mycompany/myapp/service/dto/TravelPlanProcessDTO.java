package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TravelPlanProcess} entity.
 */
public class TravelPlanProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private TravelPlanDTO travelPlan;

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

    public TravelPlanDTO getTravelPlan() {
        return travelPlan;
    }

    public void setTravelPlan(TravelPlanDTO travelPlan) {
        this.travelPlan = travelPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelPlanProcessDTO)) {
            return false;
        }

        TravelPlanProcessDTO travelPlanProcessDTO = (TravelPlanProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, travelPlanProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelPlanProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", travelPlan=" + getTravelPlan() +
            "}";
    }
}
