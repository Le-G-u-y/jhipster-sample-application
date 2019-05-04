package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.I18nTextDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link I18nText} and its DTO {@link I18nTextDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface I18nTextMapper extends EntityMapper<I18nTextDTO, I18nText> {



    default I18nText fromId(Long id) {
        if (id == null) {
            return null;
        }
        I18nText i18nText = new I18nText();
        i18nText.setId(id);
        return i18nText;
    }
}
