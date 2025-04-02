package com.onclass.model.capacity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Capacity {
    private Integer id;
    private String name;
    private String description;
    private Set<Technology> technologyIds;
}
