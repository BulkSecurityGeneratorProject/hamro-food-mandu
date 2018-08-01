package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Resturant.
 */
@Entity
@Table(name = "resturant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Resturant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "service_charge")
    private Float serviceCharge;

    @Column(name = "vat")
    private Float vat;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "resturant_opening_hour",
               joinColumns = @JoinColumn(name = "resturants_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "opening_hours_id", referencedColumnName = "id"))
    private Set<OpeningHour> openingHours = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Resturant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getServiceCharge() {
        return serviceCharge;
    }

    public Resturant serviceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
        return this;
    }

    public void setServiceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Float getVat() {
        return vat;
    }

    public Resturant vat(Float vat) {
        this.vat = vat;
        return this;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public String getDescription() {
        return description;
    }

    public Resturant description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public Resturant location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    public Resturant openingHours(Set<OpeningHour> openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public Resturant addOpeningHour(OpeningHour openingHour) {
        this.openingHours.add(openingHour);
        return this;
    }

    public Resturant removeOpeningHour(OpeningHour openingHour) {
        this.openingHours.remove(openingHour);
        return this;
    }

    public void setOpeningHours(Set<OpeningHour> openingHours) {
        this.openingHours = openingHours;
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
        Resturant resturant = (Resturant) o;
        if (resturant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resturant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resturant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceCharge=" + getServiceCharge() +
            ", vat=" + getVat() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
