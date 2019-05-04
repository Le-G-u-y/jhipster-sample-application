package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DrummerStatisticsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DrummerStatistics} and its DTO {@link DrummerStatisticsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DrummerMapper.class})
public interface DrummerStatisticsMapper extends EntityMapper<DrummerStatisticsDTO, DrummerStatistics> {

    @Mapping(source = "drummer.id", target = "drummerId")
    DrummerStatisticsDTO toDto(DrummerStatistics drummerStatistics);

    @Mapping(source = "drummerId", target = "drummer")
    DrummerStatistics toEntity(DrummerStatisticsDTO drummerStatisticsDTO);

    default DrummerStatistics fromId(Long id) {
        if (id == null) {
            return null;
        }
        DrummerStatistics drummerStatistics = new DrummerStatistics();
        drummerStatistics.setId(id);
        return drummerStatistics;
    }
}
