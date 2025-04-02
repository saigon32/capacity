package com.onclass.jpa.helper;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TechnologyCapacityDto {
    private Integer capacityId;
    private Set<Integer> technologyIds;

}
