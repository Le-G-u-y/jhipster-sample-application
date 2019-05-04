package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.FinishedExcercise} entity.
 */
public class FinishedExcerciseDTO implements Serializable {

    private Long id;

    @Min(value = 1)
    @Max(value = 500)
    private Integer actualBpm;

    @Min(value = 1)
    @Max(value = 600)
    private Integer actualMinutes;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long excerciseId;

    private Long finishedSessionId;

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

    public Integer getActualMinutes() {
        return actualMinutes;
    }

    public void setActualMinutes(Integer actualMinutes) {
        this.actualMinutes = actualMinutes;
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

    public Long getFinishedSessionId() {
        return finishedSessionId;
    }

    public void setFinishedSessionId(Long finishedSessionId) {
        this.finishedSessionId = finishedSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinishedExcerciseDTO finishedExcerciseDTO = (FinishedExcerciseDTO) o;
        if (finishedExcerciseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finishedExcerciseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinishedExcerciseDTO{" +
            "id=" + getId() +
            ", actualBpm=" + getActualBpm() +
            ", actualMinutes=" + getActualMinutes() +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", excercise=" + getExcerciseId() +
            ", finishedSession=" + getFinishedSessionId() +
            "}";
    }
}
