package com.onclass.jpa.helper;

import com.onclass.jpa.entity.CapacityTechnologyEntity;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ICapacityTechnologyEntityMapper {
    ICapacityTechnologyEntityMapper MAPPER = Mappers.getMapper(ICapacityTechnologyEntityMapper.class);

    @Mapping(target = "idCapacity", source = "capacityId")
    @Mapping(target = "idTechnology", source = "technologyId")
    CapacityTechnologyEntity toEntity(Integer capacityId, Integer technologyId);

    default List<CapacityTechnologyEntity> toEntityList(Capacity capacity) {
        Set<Technology> technologies = capacity.getTechnologyIds();

        if (technologies == null || capacity.getId() == null) {
            return List.of();
        }
        return technologies.stream()
                .map(tech -> toEntity(capacity.getId(), tech.getId()))
                .collect(Collectors.toList());
    }
}
