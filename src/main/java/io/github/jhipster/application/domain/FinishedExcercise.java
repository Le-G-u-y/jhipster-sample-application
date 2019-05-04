package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FinishedExcercise.
 */
@Entity
@Table(name = "finished_excercise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FinishedExcercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Min(value = 1)
    @Max(value = 500)
    @Column(name = "actual_bpm")
    private Integer actualBpm;

    @Min(value = 1)
    @Max(value = 600)
    @Column(name = "actual_minutes")
    private Integer actualMinutes;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

    @ManyToOne
    @JsonIgnoreProperties("finishedExcercises")
    private Excercise excercise;

    @ManyToOne
    @JsonIgnoreProperties("finishedExcercises")
    private FinishedSession finishedSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActualBpm() {
        return actualBpm;
    }

    public FinishedExcercise actualBpm(Integer actualBpm) {
        this.actualBpm = actualBpm;
        return this;
    }

    public void setActualBpm(Integer actualBpm) {
        this.actualBpm = actualBpm;
    }

    public Integer getActualMinutes() {
        return actualMinutes;
    }

    public FinishedExcercise actualMinutes(Integer actualMinutes) {
        this.actualMinutes = actualMinutes;
        return this;
    }

    public void setActualMinutes(Integer actualMinutes) {
        this.actualMinutes = actualMinutes;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public FinishedExcercise createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public FinishedExcercise modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Excercise getExcercise() {
        return excercise;
    }

    public FinishedExcercise excercise(Excercise excercise) {
        this.excercise = excercise;
        return this;
    }

    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }

    public FinishedSession getFinishedSession() {
        return finishedSession;
    }

    public FinishedExcercise finishedSession(FinishedSession finishedSession) {
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
        if (!(o instanceof FinishedExcercise)) {
            return false;
        }
        return id != null && id.equals(((FinishedExcercise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FinishedExcercise{" +
            "id=" + getId() +
            ", actualBpm=" + getActualBpm() +
            ", actualMinutes=" + getActualMinutes() +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
