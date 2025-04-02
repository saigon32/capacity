package com.onclass.api.helper.request.dto;

import com.onclass.model.capacity.Technology;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CapacityResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Set<Technology> technologyIds;
}
