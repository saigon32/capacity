package com.onclass.jpa.helper;

import com.onclass.jpa.entity.CapacityBootcampEntity;
import com.onclass.model.capacity.CapacityBootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityBootcampEntityMapper {
    CapacityBootcampEntity toEntity(CapacityBootcamp capacityBootcamp);
    CapacityBootcamp toModel(CapacityBootcampEntity capacityBootcampEntity);
    List<CapacityBootcamp> toModelList(List<CapacityBootcampEntity> capacityBootcampEntity);
    List<CapacityBootcampEntity> toEntityList(List<CapacityBootcamp> capacityBootcamp);
}
