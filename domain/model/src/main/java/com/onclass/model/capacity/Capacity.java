package com.onclass.model.capacity;

import lombok.*;

import java.util.Set;

@Builder(toBuilder = true)
@Data
public class Capacity {
    private Integer id;
    private String name;
    private String description;
    private Set<Technology> technologyIds;
}
