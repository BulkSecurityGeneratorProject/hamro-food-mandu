package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Resturant entity.
 */
public class ResturantDTO implements Serializable {

    private Long id;

    private String name;

    private Float serviceCharge;

    private Float vat;

    private String description;

    private Long locationId;

    private String locationName;

    private Set<OpeningHourDTO> openingHours = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<OpeningHourDTO> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Set<OpeningHourDTO> openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResturantDTO resturantDTO = (ResturantDTO) o;
        if (resturantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resturantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResturantDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceCharge=" + getServiceCharge() +
            ", vat=" + getVat() +
            ", description='" + getDescription() + "'" +
            ", location=" + getLocationId() +
            ", location='" + getLocationName() + "'" +
            "}";
    }
}
