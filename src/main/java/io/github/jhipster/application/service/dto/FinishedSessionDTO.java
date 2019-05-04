package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.FinishedSession} entity.
 */
public class FinishedSessionDTO implements Serializable {

    private Long id;

    @Min(value = 1)
    @Max(value = 600)
    private Integer minutesTotal;

    @Size(max = 30000)
    private String note;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinutesTotal() {
        return minutesTotal;
    }

    public void setMinutesTotal(Integer minutesTotal) {
        this.minutesTotal = minutesTotal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinishedSessionDTO finishedSessionDTO = (FinishedSessionDTO) o;
        if (finishedSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finishedSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinishedSessionDTO{" +
            "id=" + getId() +
            ", minutesTotal=" + getMinutesTotal() +
            ", note='" + getNote() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", plan=" + getPlanId() +
            "}";
    }
}
