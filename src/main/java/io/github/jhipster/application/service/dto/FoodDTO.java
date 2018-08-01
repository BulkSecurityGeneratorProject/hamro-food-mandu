package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Food entity.
 */
public class FoodDTO implements Serializable {

    private Long id;

    private String name;

    private String image;

    private Float unitPrice;

    private String description;

    private Long categoryId;

    private String categoryName;

    private Long resturantId;

    private String resturantName;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodDTO foodDTO = (FoodDTO) o;
        if (foodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FoodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", description='" + getDescription() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryName() + "'" +
            ", resturant=" + getResturantId() +
            ", resturant='" + getResturantName() + "'" +
            "}";
    }
}
