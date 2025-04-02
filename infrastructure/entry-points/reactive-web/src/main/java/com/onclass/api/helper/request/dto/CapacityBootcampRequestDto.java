package com.onclass.api.helper.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CapacityBootcampRequestDto {
    private Integer idBootcamp;
    private List<Integer> capacityIds;
}
