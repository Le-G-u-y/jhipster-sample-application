package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ExcerciseConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExcerciseConfig} and its DTO {@link ExcerciseConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExcerciseMapper.class, PlanMapper.class})
public interface ExcerciseConfigMapper extends EntityMapper<ExcerciseConfigDTO, ExcerciseConfig> {

    @Mapping(source = "excercise.id", target = "excerciseId")
    @Mapping(source = "plan.id", target = "planId")
    ExcerciseConfigDTO toDto(ExcerciseConfig excerciseConfig);

    @Mapping(source = "excerciseId", target = "excercise")
    @Mapping(source = "planId", target = "plan")
    ExcerciseConfig toEntity(ExcerciseConfigDTO excerciseConfigDTO);

    default ExcerciseConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExcerciseConfig excerciseConfig = new ExcerciseConfig();
        excerciseConfig.setId(id);
        return excerciseConfig;
    }
}
