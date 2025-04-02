package com.onclass.api.helper.mappers;

import com.onclass.api.helper.request.dto.CapacityResponseDto;
import com.onclass.api.helper.request.dto.CapacitySimpleItemResponseDto;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityResponseMapper {

    CapacityResponseDto toDto(Capacity capacity);
    List<CapacityResponseDto> toDtoList(List<Capacity> capacities);
    CapacitySimpleItemResponseDto modelToSimpleItemResponseDto(Capacity capacity);

}
