package com.onclass.jpa.adapter.port;

import com.onclass.jpa.entity.CapacityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICapacityTechnologyRepository extends ReactiveCrudRepository<CapacityTechnologyEntity, Integer> {
}
