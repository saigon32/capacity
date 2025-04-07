package com.onclass.model.capacity;

import lombok.*;

import java.util.Set;

@Data
public class Capacity {
    private Integer id;
    private String name;
    private String description;
    private Set<Technology> technologyIds;
}
