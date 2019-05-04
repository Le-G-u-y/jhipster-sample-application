package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PlanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Plan} and its DTO {@link PlanDTO}.
 */
@Mapper(componentModel = "spring", uses = {DrummerMapper.class})
public interface PlanMapper extends EntityMapper<PlanDTO, Plan> {

    @Mapping(source = "creator.id", target = "creatorId")
    @Mapping(source = "owner.id", target = "ownerId")
    PlanDTO toDto(Plan plan);

    @Mapping(target = "excerciseConfigs", ignore = true)
    @Mapping(source = "creatorId", target = "creator")
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "finishedSession", ignore = true)
    Plan toEntity(PlanDTO planDTO);

    default Plan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Plan plan = new Plan();
        plan.setId(id);
        return plan;
    }
}
