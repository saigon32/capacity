package com.onclass.api.helper.mappers;

import com.onclass.api.helper.request.dto.CapacityRequestDto;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.Technology;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICapacityRequestMapper {

    @Mapping(target = "technologyIds", source = "technologyIds", qualifiedByName = "mapTechnologyIdsToTechnology")
    Capacity toDomain(CapacityRequestDto capacityRequestDto);

    @Named("mapTechnologyIdsToTechnology")
    default Set<Technology> mapTechnologyIdsToTechnology(Set<Integer> technologyIds) {
        // Crear un set de Technology con solo los IDs (nombre como null)
        return technologyIds.stream()
                .map(id -> new Technology(id, null))
                .collect(Collectors.toSet());
    }

}
