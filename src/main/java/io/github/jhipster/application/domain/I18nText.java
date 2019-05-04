package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Noch zu lösen:
 * - Entscheidungen Internationalisierung:
 * - Pläne/Übungen von Drumbitious sind internationalisiert.
 * - Selbsterstellte Pläne sind nicht internationalisiert
 * -
 * 
 * https:
 */
@Entity
@Table(name = "i_18_n_text")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class I18nText implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "locale", nullable = false)
    private String locale;

    @NotNull
    @Column(name = "text_key", nullable = false)
    private String textKey;

    @Column(name = "text_content")
    private String textContent;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public I18nText locale(String locale) {
        this.locale = locale;
        return this;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTextKey() {
        return textKey;
    }

    public I18nText textKey(String textKey) {
        this.textKey = textKey;
        return this;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public String getTextContent() {
        return textContent;
    }

    public I18nText textContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public I18nText createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public I18nText modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof I18nText)) {
            return false;
        }
        return id != null && id.equals(((I18nText) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "I18nText{" +
            "id=" + getId() +
            ", locale='" + getLocale() + "'" +
            ", textKey='" + getTextKey() + "'" +
            ", textContent='" + getTextContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
