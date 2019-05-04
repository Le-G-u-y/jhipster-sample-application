package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.SkillType;
import io.github.jhipster.application.domain.enumeration.ExcerciseType;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Excercise} entity.
 */
public class ExcerciseDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String excerciseName;

    @Size(max = 30000)
    private String description;

    @Max(value = 9000)
    private Integer defaultMinutes;

    @Min(value = 1)
    @Max(value = 500)
    private Integer defaultTargetBpm;

    private SkillType skillType;

    private ExcerciseType excerciseType;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExcerciseName() {
        return excerciseName;
    }

    public void setExcerciseName(String excerciseName) {
        this.excerciseName = excerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultMinutes() {
        return defaultMinutes;
    }

    public void setDefaultMinutes(Integer defaultMinutes) {
        this.defaultMinutes = defaultMinutes;
    }

    public Integer getDefaultTargetBpm() {
        return defaultTargetBpm;
    }

    public void setDefaultTargetBpm(Integer defaultTargetBpm) {
        this.defaultTargetBpm = defaultTargetBpm;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public ExcerciseType getExcerciseType() {
        return excerciseType;
    }

    public void setExcerciseType(ExcerciseType excerciseType) {
        this.excerciseType = excerciseType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExcerciseDTO excerciseDTO = (ExcerciseDTO) o;
        if (excerciseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), excerciseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExcerciseDTO{" +
            "id=" + getId() +
            ", excerciseName='" + getExcerciseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", defaultMinutes=" + getDefaultMinutes() +
            ", defaultTargetBpm=" + getDefaultTargetBpm() +
            ", skillType='" + getSkillType() + "'" +
            ", excerciseType='" + getExcerciseType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", creator=" + getCreatorId() +
            "}";
    }
}
