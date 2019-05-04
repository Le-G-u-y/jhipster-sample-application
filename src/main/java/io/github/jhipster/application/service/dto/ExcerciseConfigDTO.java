package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ExcerciseConfig} entity.
 */
public class ExcerciseConfigDTO implements Serializable {

    private Long id;

    @Min(value = 1)
    @Max(value = 500)
    private Integer actualBpm;

    @Min(value = 1)
    @Max(value = 500)
    private Integer targetBpm;

    @Min(value = 1)
    @Max(value = 500)
    private Integer minutes;

    @Size(max = 30000)
    private String note;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long excerciseId;

    private Long planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActualBpm() {
        return actualBpm;
    }

    public void setActualBpm(Integer actualBpm) {
        this.actualBpm = actualBpm;
    }

    public Integer getTargetBpm() {
        return targetBpm;
    }

    public void setTargetBpm(Integer targetBpm) {
        this.targetBpm = targetBpm;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
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

    public Long getExcerciseId() {
        return excerciseId;
    }

    public void setExcerciseId(Long excerciseId) {
        this.excerciseId = excerciseId;
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

        ExcerciseConfigDTO excerciseConfigDTO = (ExcerciseConfigDTO) o;
        if (excerciseConfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), excerciseConfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExcerciseConfigDTO{" +
            "id=" + getId() +
            ", actualBpm=" + getActualBpm() +
            ", targetBpm=" + getTargetBpm() +
            ", minutes=" + getMinutes() +
            ", note='" + getNote() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", excercise=" + getExcerciseId() +
            ", plan=" + getPlanId() +
            "}";
    }
}
