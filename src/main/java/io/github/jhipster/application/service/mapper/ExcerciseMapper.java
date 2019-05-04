package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ExcerciseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Excercise} and its DTO {@link ExcerciseDTO}.
 */
@Mapper(componentModel = "spring", uses = {DrummerMapper.class})
public interface ExcerciseMapper extends EntityMapper<ExcerciseDTO, Excercise> {

    @Mapping(source = "creator.id", target = "creatorId")
    ExcerciseDTO toDto(Excercise excercise);

    @Mapping(source = "creatorId", target = "creator")
    @Mapping(target = "excerciseConfigs", ignore = true)
    @Mapping(target = "finishedExcercises", ignore = true)
    Excercise toEntity(ExcerciseDTO excerciseDTO);

    default Excercise fromId(Long id) {
        if (id == null) {
            return null;
        }
        Excercise excercise = new Excercise();
        excercise.setId(id);
        return excercise;
    }
}
