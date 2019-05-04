package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DrummerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Drummer} and its DTO {@link DrummerDTO}.
 */
@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface DrummerMapper extends EntityMapper<DrummerDTO, Drummer> {

    @Mapping(source = "role.id", target = "roleId")
    DrummerDTO toDto(Drummer drummer);

    @Mapping(source = "roleId", target = "role")
    @Mapping(target = "drummerStatistics", ignore = true)
    Drummer toEntity(DrummerDTO drummerDTO);

    default Drummer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Drummer drummer = new Drummer();
        drummer.setId(id);
        return drummer;
    }
}
