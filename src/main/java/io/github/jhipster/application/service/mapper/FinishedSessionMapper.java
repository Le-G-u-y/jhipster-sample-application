package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FinishedSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinishedSession} and its DTO {@link FinishedSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlanMapper.class})
public interface FinishedSessionMapper extends EntityMapper<FinishedSessionDTO, FinishedSession> {

    @Mapping(source = "plan.id", target = "planId")
    FinishedSessionDTO toDto(FinishedSession finishedSession);

    @Mapping(source = "planId", target = "plan")
    @Mapping(target = "finishedExcercises", ignore = true)
    FinishedSession toEntity(FinishedSessionDTO finishedSessionDTO);

    default FinishedSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinishedSession finishedSession = new FinishedSession();
        finishedSession.setId(id);
        return finishedSession;
    }
}
