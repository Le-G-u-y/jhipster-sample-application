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

import io.github.jhipster.application.domain.enumeration.SkillType;

import io.github.jhipster.application.domain.enumeration.ExcerciseType;

/**
 * A Excercise.
 */
@Entity
@Table(name = "excercise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Excercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "excercise_name", length = 200, nullable = false)
    private String excerciseName;

    @Size(max = 30000)
    @Column(name = "description", length = 30000)
    private String description;

    @Max(value = 9000)
    @Column(name = "default_minutes")
    private Integer defaultMinutes;

    @Min(value = 1)
    @Max(value = 500)
    @Column(name = "default_target_bpm")
    private Integer defaultTargetBpm;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_type")
    private SkillType skillType;

    @Enumerated(EnumType.STRING)
    @Column(name = "excercise_type")
    private ExcerciseType excerciseType;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

    @ManyToOne
    @JsonIgnoreProperties("excercises")
    private Drummer creator;

    @OneToMany(mappedBy = "excercise")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExcerciseConfig> excerciseConfigs = new HashSet<>();

    @OneToMany(mappedBy = "excercise")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinishedExcercise> finishedExcercises = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExcerciseName() {
        return excerciseName;
    }

    public Excercise excerciseName(String excerciseName) {
        this.excerciseName = excerciseName;
        return this;
    }

    public void setExcerciseName(String excerciseName) {
        this.excerciseName = excerciseName;
    }

    public String getDescription() {
        return description;
    }

    public Excercise description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultMinutes() {
        return defaultMinutes;
    }

    public Excercise defaultMinutes(Integer defaultMinutes) {
        this.defaultMinutes = defaultMinutes;
        return this;
    }

    public void setDefaultMinutes(Integer defaultMinutes) {
        this.defaultMinutes = defaultMinutes;
    }

    public Integer getDefaultTargetBpm() {
        return defaultTargetBpm;
    }

    public Excercise defaultTargetBpm(Integer defaultTargetBpm) {
        this.defaultTargetBpm = defaultTargetBpm;
        return this;
    }

    public void setDefaultTargetBpm(Integer defaultTargetBpm) {
        this.defaultTargetBpm = defaultTargetBpm;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public Excercise skillType(SkillType skillType) {
        this.skillType = skillType;
        return this;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public ExcerciseType getExcerciseType() {
        return excerciseType;
    }

    public Excercise excerciseType(ExcerciseType excerciseType) {
        this.excerciseType = excerciseType;
        return this;
    }

    public void setExcerciseType(ExcerciseType excerciseType) {
        this.excerciseType = excerciseType;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Excercise createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Excercise modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Drummer getCreator() {
        return creator;
    }

    public Excercise creator(Drummer drummer) {
        this.creator = drummer;
        return this;
    }

    public void setCreator(Drummer drummer) {
        this.creator = drummer;
    }

    public Set<ExcerciseConfig> getExcerciseConfigs() {
        return excerciseConfigs;
    }

    public Excercise excerciseConfigs(Set<ExcerciseConfig> excerciseConfigs) {
        this.excerciseConfigs = excerciseConfigs;
        return this;
    }

    public Excercise addExcerciseConfig(ExcerciseConfig excerciseConfig) {
        this.excerciseConfigs.add(excerciseConfig);
        excerciseConfig.setExcercise(this);
        return this;
    }

    public Excercise removeExcerciseConfig(ExcerciseConfig excerciseConfig) {
        this.excerciseConfigs.remove(excerciseConfig);
        excerciseConfig.setExcercise(null);
        return this;
    }

    public void setExcerciseConfigs(Set<ExcerciseConfig> excerciseConfigs) {
        this.excerciseConfigs = excerciseConfigs;
    }

    public Set<FinishedExcercise> getFinishedExcercises() {
        return finishedExcercises;
    }

    public Excercise finishedExcercises(Set<FinishedExcercise> finishedExcercises) {
        this.finishedExcercises = finishedExcercises;
        return this;
    }

    public Excercise addFinishedExcercise(FinishedExcercise finishedExcercise) {
        this.finishedExcercises.add(finishedExcercise);
        finishedExcercise.setExcercise(this);
        return this;
    }

    public Excercise removeFinishedExcercise(FinishedExcercise finishedExcercise) {
        this.finishedExcercises.remove(finishedExcercise);
        finishedExcercise.setExcercise(null);
        return this;
    }

    public void setFinishedExcercises(Set<FinishedExcercise> finishedExcercises) {
        this.finishedExcercises = finishedExcercises;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Excercise)) {
            return false;
        }
        return id != null && id.equals(((Excercise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Excercise{" +
            "id=" + getId() +
            ", excerciseName='" + getExcerciseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", defaultMinutes=" + getDefaultMinutes() +
            ", defaultTargetBpm=" + getDefaultTargetBpm() +
            ", skillType='" + getSkillType() + "'" +
            ", excerciseType='" + getExcerciseType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
