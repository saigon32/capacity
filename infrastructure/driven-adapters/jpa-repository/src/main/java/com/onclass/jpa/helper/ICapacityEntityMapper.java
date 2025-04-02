package com.onclass.jpa.helper;

import com.onclass.jpa.entity.CapacityEntity;

import com.onclass.model.capacity.Capacity;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityEntityMapper {
    @Mapping(target = "id", source = "id")
    Capacity toModel(CapacityEntity entity);
    @Mapping(target = "id", source = "id")
    CapacityEntity toEntity(Capacity capacity);

}
