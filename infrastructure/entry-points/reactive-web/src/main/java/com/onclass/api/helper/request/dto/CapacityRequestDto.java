package com.onclass.api.helper.request.dto;

import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CapacityRequestDto {
    private String name;
    private String description;
    private Set<Integer> technologyIds;
}
