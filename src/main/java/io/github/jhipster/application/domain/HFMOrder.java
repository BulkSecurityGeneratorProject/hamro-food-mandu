package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A HFMOrder.
 */
@Entity
@Table(name = "hfm_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HFMOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "total_charge")
    private Float totalCharge;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private Tray tray;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PaymentType payment;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DeliveryInfo deliveryInfo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Resturant resturant;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public HFMOrder status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public HFMOrder creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public HFMOrder completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public HFMOrder deliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Float getTotalCharge() {
        return totalCharge;
    }

    public HFMOrder totalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
        return this;
    }

    public void setTotalCharge(Float totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getDescription() {
        return description;
    }

    public HFMOrder description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tray getTray() {
        return tray;
    }

    public HFMOrder tray(Tray tray) {
        this.tray = tray;
        return this;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public HFMOrder payment(PaymentType paymentType) {
        this.payment = paymentType;
        return this;
    }

    public void setPayment(PaymentType paymentType) {
        this.payment = paymentType;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public HFMOrder deliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        return this;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public HFMOrder resturant(Resturant resturant) {
        this.resturant = resturant;
        return this;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

    public User getUser() {
        return user;
    }

    public HFMOrder user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        HFMOrder hFMOrder = (HFMOrder) o;
        if (hFMOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hFMOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HFMOrder{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", totalCharge=" + getTotalCharge() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
