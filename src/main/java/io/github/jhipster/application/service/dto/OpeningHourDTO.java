package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OpeningHour entity.
 */
public class OpeningHourDTO implements Serializable {

    private Long id;

    private String day;

    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OpeningHourDTO openingHourDTO = (OpeningHourDTO) o;
        if (openingHourDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), openingHourDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OpeningHourDTO{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
