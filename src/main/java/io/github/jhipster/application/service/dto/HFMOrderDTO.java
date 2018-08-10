package io.github.jhipster.application.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the HFMOrder entity.
 */
public class HFMOrderDTO implements Serializable {

    private Long id;

    private String status;

    private LocalDate creationDate;

    private LocalDate completionDate;

    private LocalDate deliveryDate;

    private Float totalCharge;

    private String description;

    private Long trayId;

    private Long paymentId;

    private String paymentName;

    private Long deliveryInfoId;

    private Long resturantId;

    private String resturantName;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Float getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTrayId() {
        return trayId;
    }

    public void setTrayId(Long trayId) {
        this.trayId = trayId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentTypeId) {
        this.paymentId = paymentTypeId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentTypeName) {
        this.paymentName = paymentTypeName;
    }

    public Long getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public void setDeliveryInfoId(Long deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
    }

    public Long getResturantId() {
        return resturantId;
    }

    public void setResturantId(Long resturantId) {
        this.resturantId = resturantId;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HFMOrderDTO hFMOrderDTO = (HFMOrderDTO) o;
        if (hFMOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hFMOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HFMOrderDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", totalCharge=" + getTotalCharge() +
            ", description='" + getDescription() + "'" +
            ", tray=" + getTrayId() +
            ", payment=" + getPaymentId() +
            ", payment='" + getPaymentName() + "'" +
            ", deliveryInfo=" + getDeliveryInfoId() +
            ", resturant=" + getResturantId() +
            ", resturant='" + getResturantName() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
