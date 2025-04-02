package com.onclass.jpa.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacity_bootcamp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CapacityBootcampEntity {
    private Integer id;
    @Column("id_capacity")
    private Integer idCapacity;
    @Column("id_bootcamp")
    private Integer idBootcamp;
}
