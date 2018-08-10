package io.github.jhipster.application.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tray entity.
 */
public class TrayDTO implements Serializable {

    private Long id;

    private String status;

    private LocalDate creationDate;

    private String extra;

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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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

        TrayDTO trayDTO = (TrayDTO) o;
        if (trayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrayDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", extra='" + getExtra() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
