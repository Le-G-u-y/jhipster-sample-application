package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.I18nText} entity.
 */
@ApiModel(description = "Noch zu lösen: - Entscheidungen Internationalisierung: - Pläne/Übungen von Drumbitious sind internationalisiert. - Selbsterstellte Pläne sind nicht internationalisiert - https:")
public class I18nTextDTO implements Serializable {

    private Long id;

    @NotNull
    private String locale;

    @NotNull
    private String textKey;

    private String textContent;

    @NotNull
    private Instant createDate;

    @NotNull
    private Instant modifyDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        I18nTextDTO i18nTextDTO = (I18nTextDTO) o;
        if (i18nTextDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), i18nTextDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "I18nTextDTO{" +
            "id=" + getId() +
            ", locale='" + getLocale() + "'" +
            ", textKey='" + getTextKey() + "'" +
            ", textContent='" + getTextContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
