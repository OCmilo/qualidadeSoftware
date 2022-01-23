package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TravelPlan.
 */
@Entity
@Table(name = "travel_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TravelPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "airline_company_name")
    private String airlineCompanyName;

    @Column(name = "airline_ticket_number")
    private String airlineTicketNumber;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_booking_number")
    private String hotelBookingNumber;

    @Column(name = "car_company_name")
    private String carCompanyName;

    @Column(name = "car_booking_number")
    private String carBookingNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelPlan id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public TravelPlan name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public TravelPlan startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public TravelPlan endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAirlineCompanyName() {
        return this.airlineCompanyName;
    }

    public TravelPlan airlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
        return this;
    }

    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
    }

    public String getAirlineTicketNumber() {
        return this.airlineTicketNumber;
    }

    public TravelPlan airlineTicketNumber(String airlineTicketNumber) {
        this.airlineTicketNumber = airlineTicketNumber;
        return this;
    }

    public void setAirlineTicketNumber(String airlineTicketNumber) {
        this.airlineTicketNumber = airlineTicketNumber;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public TravelPlan hotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelBookingNumber() {
        return this.hotelBookingNumber;
    }

    public TravelPlan hotelBookingNumber(String hotelBookingNumber) {
        this.hotelBookingNumber = hotelBookingNumber;
        return this;
    }

    public void setHotelBookingNumber(String hotelBookingNumber) {
        this.hotelBookingNumber = hotelBookingNumber;
    }

    public String getCarCompanyName() {
        return this.carCompanyName;
    }

    public TravelPlan carCompanyName(String carCompanyName) {
        this.carCompanyName = carCompanyName;
        return this;
    }

    public void setCarCompanyName(String carCompanyName) {
        this.carCompanyName = carCompanyName;
    }

    public String getCarBookingNumber() {
        return this.carBookingNumber;
    }

    public TravelPlan carBookingNumber(String carBookingNumber) {
        this.carBookingNumber = carBookingNumber;
        return this;
    }

    public void setCarBookingNumber(String carBookingNumber) {
        this.carBookingNumber = carBookingNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelPlan)) {
            return false;
        }
        return id != null && id.equals(((TravelPlan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelPlan{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", airlineCompanyName='" + getAirlineCompanyName() + "'" +
            ", airlineTicketNumber='" + getAirlineTicketNumber() + "'" +
            ", hotelName='" + getHotelName() + "'" +
            ", hotelBookingNumber='" + getHotelBookingNumber() + "'" +
            ", carCompanyName='" + getCarCompanyName() + "'" +
            ", carBookingNumber='" + getCarBookingNumber() + "'" +
            "}";
    }
}
