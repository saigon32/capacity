package com.onclass.jpa.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacity_technology")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CapacityTechnologyEntity {
    @Column("id_capacity")
    private Integer idCapacity;
    @Column("id_technology")
    private String idTechnology;
}