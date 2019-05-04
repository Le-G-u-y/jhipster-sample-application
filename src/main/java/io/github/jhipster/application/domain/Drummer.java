package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Drummer.
 */
@Entity
@Table(name = "drummer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Drummer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "drummer_name", nullable = false)
    private String drummerName;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

    @ManyToOne
    @JsonIgnoreProperties("drummers")
    private Role role;

    @OneToOne(mappedBy = "drummer")
    @JsonIgnore
    private DrummerStatistics drummerStatistics;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrummerName() {
        return drummerName;
    }

    public Drummer drummerName(String drummerName) {
        this.drummerName = drummerName;
        return this;
    }

    public void setDrummerName(String drummerName) {
        this.drummerName = drummerName;
    }

    public String getDescription() {
        return description;
    }

    public Drummer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public Drummer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Drummer createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Drummer modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Role getRole() {
        return role;
    }

    public Drummer role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DrummerStatistics getDrummerStatistics() {
        return drummerStatistics;
    }

    public Drummer drummerStatistics(DrummerStatistics drummerStatistics) {
        this.drummerStatistics = drummerStatistics;
        return this;
    }

    public void setDrummerStatistics(DrummerStatistics drummerStatistics) {
        this.drummerStatistics = drummerStatistics;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Drummer)) {
            return false;
        }
        return id != null && id.equals(((Drummer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Drummer{" +
            "id=" + getId() +
            ", drummerName='" + getDrummerName() + "'" +
            ", description='" + getDescription() + "'" +
            ", email='" + getEmail() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
