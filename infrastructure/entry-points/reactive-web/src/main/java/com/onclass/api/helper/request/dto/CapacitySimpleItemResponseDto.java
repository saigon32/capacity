package com.onclass.api.helper.request.dto;

import com.onclass.model.capacity.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CapacitySimpleItemResponseDto {
    private Integer id;
    private String name;
    private Set<Technology> technologies;

}
