package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FinishedExcerciseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinishedExcercise} and its DTO {@link FinishedExcerciseDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExcerciseMapper.class, FinishedSessionMapper.class})
public interface FinishedExcerciseMapper extends EntityMapper<FinishedExcerciseDTO, FinishedExcercise> {

    @Mapping(source = "excercise.id", target = "excerciseId")
    @Mapping(source = "finishedSession.id", target = "finishedSessionId")
    FinishedExcerciseDTO toDto(FinishedExcercise finishedExcercise);

    @Mapping(source = "excerciseId", target = "excercise")
    @Mapping(source = "finishedSessionId", target = "finishedSession")
    FinishedExcercise toEntity(FinishedExcerciseDTO finishedExcerciseDTO);

    default FinishedExcercise fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinishedExcercise finishedExcercise = new FinishedExcercise();
        finishedExcercise.setId(id);
        return finishedExcercise;
    }
}
