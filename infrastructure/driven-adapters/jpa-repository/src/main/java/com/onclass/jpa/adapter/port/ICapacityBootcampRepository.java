package com.onclass.jpa.adapter.port;

import com.onclass.jpa.entity.CapacityBootcampEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ICapacityBootcampRepository extends ReactiveCrudRepository<CapacityBootcampEntity, Integer> {
    Mono<CapacityBootcampEntity> save(CapacityBootcampEntity capacityBootcampEntity);
}
