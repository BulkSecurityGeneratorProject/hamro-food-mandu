package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A TrayFood.
 */
@Entity
@Table(name = "tray_food")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrayFood implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_charge")
    private Float totalCharge;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "extra")
    private String extra;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Tray tray;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TrayFood quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalCharge() {
        return totalCharge;
    }

    public TrayFood totalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
        return this;
    }

    public void setTotalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public TrayFood creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getExtra() {
        return extra;
    }

    public TrayFood extra(String extra) {
        this.extra = extra;
        return this;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Tray getTray() {
        return tray;
    }

    public TrayFood tray(Tray tray) {
        this.tray = tray;
        return this;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public Food getFood() {
        return food;
    }

    public TrayFood food(Food food) {
        this.food = food;
        return this;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrayFood trayFood = (TrayFood) o;
        if (trayFood.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trayFood.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrayFood{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", totalCharge=" + getTotalCharge() +
            ", creationDate='" + getCreationDate() + "'" +
            ", extra='" + getExtra() + "'" +
            "}";
    }
}
