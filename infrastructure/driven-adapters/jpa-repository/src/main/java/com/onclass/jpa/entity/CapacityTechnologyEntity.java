package com.onclass.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacity.capacity_technology")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CapacityTechnologyEntity {

    @Id
    private Integer id;

    @Column("id_capacity")
    private Integer idCapacity;

    @Column("id_technology")
    private String idTechnology;
}