package io.github.jhipster.application.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.DrummerStatistics} entity.
 */
public class DrummerStatisticsDTO implements Serializable {

    private Long id;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelSpeed;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelGroove;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelCreativity;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelAdaptability;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelDynamics;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelIndependence;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelLivePerformance;

    @Min(value = 0)
    @Max(value = 10)
    private Integer selfAssessedLevelReadingMusic;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    private Long drummerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSelfAssessedLevelSpeed() {
        return selfAssessedLevelSpeed;
    }

    public void setSelfAssessedLevelSpeed(Integer selfAssessedLevelSpeed) {
        this.selfAssessedLevelSpeed = selfAssessedLevelSpeed;
    }

    public Integer getSelfAssessedLevelGroove() {
        return selfAssessedLevelGroove;
    }

    public void setSelfAssessedLevelGroove(Integer selfAssessedLevelGroove) {
        this.selfAssessedLevelGroove = selfAssessedLevelGroove;
    }

    public Integer getSelfAssessedLevelCreativity() {
        return selfAssessedLevelCreativity;
    }

    public void setSelfAssessedLevelCreativity(Integer selfAssessedLevelCreativity) {
        this.selfAssessedLevelCreativity = selfAssessedLevelCreativity;
    }

    public Integer getSelfAssessedLevelAdaptability() {
        return selfAssessedLevelAdaptability;
    }

    public void setSelfAssessedLevelAdaptability(Integer selfAssessedLevelAdaptability) {
        this.selfAssessedLevelAdaptability = selfAssessedLevelAdaptability;
    }

    public Integer getSelfAssessedLevelDynamics() {
        return selfAssessedLevelDynamics;
    }

    public void setSelfAssessedLevelDynamics(Integer selfAssessedLevelDynamics) {
        this.selfAssessedLevelDynamics = selfAssessedLevelDynamics;
    }

    public Integer getSelfAssessedLevelIndependence() {
        return selfAssessedLevelIndependence;
    }

    public void setSelfAssessedLevelIndependence(Integer selfAssessedLevelIndependence) {
        this.selfAssessedLevelIndependence = selfAssessedLevelIndependence;
    }

    public Integer getSelfAssessedLevelLivePerformance() {
        return selfAssessedLevelLivePerformance;
    }

    public void setSelfAssessedLevelLivePerformance(Integer selfAssessedLevelLivePerformance) {
        this.selfAssessedLevelLivePerformance = selfAssessedLevelLivePerformance;
    }

    public Integer getSelfAssessedLevelReadingMusic() {
        return selfAssessedLevelReadingMusic;
    }

    public void setSelfAssessedLevelReadingMusic(Integer selfAssessedLevelReadingMusic) {
        this.selfAssessedLevelReadingMusic = selfAssessedLevelReadingMusic;
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

    public Long getDrummerId() {
        return drummerId;
    }

    public void setDrummerId(Long drummerId) {
        this.drummerId = drummerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DrummerStatisticsDTO drummerStatisticsDTO = (DrummerStatisticsDTO) o;
        if (drummerStatisticsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), drummerStatisticsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DrummerStatisticsDTO{" +
            "id=" + getId() +
            ", selfAssessedLevelSpeed=" + getSelfAssessedLevelSpeed() +
            ", selfAssessedLevelGroove=" + getSelfAssessedLevelGroove() +
            ", selfAssessedLevelCreativity=" + getSelfAssessedLevelCreativity() +
            ", selfAssessedLevelAdaptability=" + getSelfAssessedLevelAdaptability() +
            ", selfAssessedLevelDynamics=" + getSelfAssessedLevelDynamics() +
            ", selfAssessedLevelIndependence=" + getSelfAssessedLevelIndependence() +
            ", selfAssessedLevelLivePerformance=" + getSelfAssessedLevelLivePerformance() +
            ", selfAssessedLevelReadingMusic=" + getSelfAssessedLevelReadingMusic() +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", drummer=" + getDrummerId() +
            "}";
    }
}
