package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Drummer} entity.
 */
public class DrummerDTO implements Serializable {

    private Long id;

    @NotNull
    private String drummerName;

    private String description;

    @NotNull
    private String email;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrummerName() {
        return drummerName;
    }

    public void setDrummerName(String drummerName) {
        this.drummerName = drummerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DrummerDTO drummerDTO = (DrummerDTO) o;
        if (drummerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), drummerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DrummerDTO{" +
            "id=" + getId() +
            ", drummerName='" + getDrummerName() + "'" +
            ", description='" + getDescription() + "'" +
            ", email='" + getEmail() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", role=" + getRoleId() +
            "}";
    }
}
