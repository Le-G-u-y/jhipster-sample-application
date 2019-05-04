package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Plan} entity.
 */
@ApiModel(description = "Practice Plan that gets")
public class PlanDTO implements Serializable {

    private Long id;

    @NotNull
    private String planName;

    @NotNull
    private String planFocus;

    private String description;

    private Integer minutesPerSession;

    private Integer sessionsPerWeek;

    private Instant targetDate;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long creatorId;

    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanFocus() {
        return planFocus;
    }

    public void setPlanFocus(String planFocus) {
        this.planFocus = planFocus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinutesPerSession() {
        return minutesPerSession;
    }

    public void setMinutesPerSession(Integer minutesPerSession) {
        this.minutesPerSession = minutesPerSession;
    }

    public Integer getSessionsPerWeek() {
        return sessionsPerWeek;
    }

    public void setSessionsPerWeek(Integer sessionsPerWeek) {
        this.sessionsPerWeek = sessionsPerWeek;
    }

    public Instant getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Instant targetDate) {
        this.targetDate = targetDate;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long drummerId) {
        this.creatorId = drummerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long drummerId) {
        this.ownerId = drummerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanDTO planDTO = (PlanDTO) o;
        if (planDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanDTO{" +
            "id=" + getId() +
            ", planName='" + getPlanName() + "'" +
            ", planFocus='" + getPlanFocus() + "'" +
            ", description='" + getDescription() + "'" +
            ", minutesPerSession=" + getMinutesPerSession() +
            ", sessionsPerWeek=" + getSessionsPerWeek() +
            ", targetDate='" + getTargetDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", creator=" + getCreatorId() +
            ", owner=" + getOwnerId() +
            "}";
    }
}
