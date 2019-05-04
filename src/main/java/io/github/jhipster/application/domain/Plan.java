package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Practice Plan that gets
 */
@Entity
@Table(name = "plan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "plan_name", nullable = false)
    private String planName;

    @NotNull
    @Column(name = "plan_focus", nullable = false)
    private String planFocus;

    @Column(name = "description")
    private String description;

    @Column(name = "minutes_per_session")
    private Integer minutesPerSession;

    @Column(name = "sessions_per_week")
    private Integer sessionsPerWeek;

    @Column(name = "target_date")
    private Instant targetDate;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

    @OneToMany(mappedBy = "plan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExcerciseConfig> excerciseConfigs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("plans")
    private Drummer creator;

    @ManyToOne
    @JsonIgnoreProperties("plans")
    private Drummer owner;

    @OneToOne(mappedBy = "plan")
    @JsonIgnore
    private FinishedSession finishedSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public Plan planName(String planName) {
        this.planName = planName;
        return this;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanFocus() {
        return planFocus;
    }

    public Plan planFocus(String planFocus) {
        this.planFocus = planFocus;
        return this;
    }

    public void setPlanFocus(String planFocus) {
        this.planFocus = planFocus;
    }

    public String getDescription() {
        return description;
    }

    public Plan description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinutesPerSession() {
        return minutesPerSession;
    }

    public Plan minutesPerSession(Integer minutesPerSession) {
        this.minutesPerSession = minutesPerSession;
        return this;
    }

    public void setMinutesPerSession(Integer minutesPerSession) {
        this.minutesPerSession = minutesPerSession;
    }

    public Integer getSessionsPerWeek() {
        return sessionsPerWeek;
    }

    public Plan sessionsPerWeek(Integer sessionsPerWeek) {
        this.sessionsPerWeek = sessionsPerWeek;
        return this;
    }

    public void setSessionsPerWeek(Integer sessionsPerWeek) {
        this.sessionsPerWeek = sessionsPerWeek;
    }

    public Instant getTargetDate() {
        return targetDate;
    }

    public Plan targetDate(Instant targetDate) {
        this.targetDate = targetDate;
        return this;
    }

    public void setTargetDate(Instant targetDate) {
        this.targetDate = targetDate;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Plan createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Plan modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<ExcerciseConfig> getExcerciseConfigs() {
        return excerciseConfigs;
    }

    public Plan excerciseConfigs(Set<ExcerciseConfig> excerciseConfigs) {
        this.excerciseConfigs = excerciseConfigs;
        return this;
    }

    public Plan addExcerciseConfig(ExcerciseConfig excerciseConfig) {
        this.excerciseConfigs.add(excerciseConfig);
        excerciseConfig.setPlan(this);
        return this;
    }

    public Plan removeExcerciseConfig(ExcerciseConfig excerciseConfig) {
        this.excerciseConfigs.remove(excerciseConfig);
        excerciseConfig.setPlan(null);
        return this;
    }

    public void setExcerciseConfigs(Set<ExcerciseConfig> excerciseConfigs) {
        this.excerciseConfigs = excerciseConfigs;
    }

    public Drummer getCreator() {
        return creator;
    }

    public Plan creator(Drummer drummer) {
        this.creator = drummer;
        return this;
    }

    public void setCreator(Drummer drummer) {
        this.creator = drummer;
    }

    public Drummer getOwner() {
        return owner;
    }

    public Plan owner(Drummer drummer) {
        this.owner = drummer;
        return this;
    }

    public void setOwner(Drummer drummer) {
        this.owner = drummer;
    }

    public FinishedSession getFinishedSession() {
        return finishedSession;
    }

    public Plan finishedSession(FinishedSession finishedSession) {
        this.finishedSession = finishedSession;
        return this;
    }

    public void setFinishedSession(FinishedSession finishedSession) {
        this.finishedSession = finishedSession;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plan)) {
            return false;
        }
        return id != null && id.equals(((Plan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Plan{" +
            "id=" + getId() +
            ", planName='" + getPlanName() + "'" +
            ", planFocus='" + getPlanFocus() + "'" +
            ", description='" + getDescription() + "'" +
            ", minutesPerSession=" + getMinutesPerSession() +
            ", sessionsPerWeek=" + getSessionsPerWeek() +
            ", targetDate='" + getTargetDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
