package io.github.jhipster.application.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TrayFood entity.
 */
public class TrayFoodDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private Float totalCharge;

    private LocalDate creationDate;

    private String extra;

    private Long trayId;

    private Long foodId;

    private String foodName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Long getTrayId() {
        return trayId;
    }

    public void setTrayId(Long trayId) {
        this.trayId = trayId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrayFoodDTO trayFoodDTO = (TrayFoodDTO) o;
        if (trayFoodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trayFoodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrayFoodDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", totalCharge=" + getTotalCharge() +
            ", creationDate='" + getCreationDate() + "'" +
            ", extra='" + getExtra() + "'" +
            ", tray=" + getTrayId() +
            ", food=" + getFoodId() +
            ", food='" + getFoodName() + "'" +
            "}";
    }
}
