package com.onclass.jpa.adapter.port;

import com.onclass.jpa.entity.CapacityBootcampEntity;
import com.onclass.jpa.entity.CapacityEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ICapacityRepository extends ReactiveCrudRepository<CapacityEntity, Integer> {
    Mono<CapacityEntity> findByName(String name);

    Flux<CapacityEntity> findAllBy(/*Pageable pageable*/PageRequest pageRequest);

    Flux<CapacityBootcampEntity> saveAll(List<CapacityBootcampEntity> abilityBootcampEntities);
}
