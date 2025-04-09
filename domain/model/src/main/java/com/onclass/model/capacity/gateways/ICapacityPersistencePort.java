package com.onclass.model.capacity.gateways;

import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.CapacityBootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityPersistencePort {
    Mono<Capacity> saveCapacity(Capacity capacity);

    Mono<Capacity> findByName(String name);

    Flux<Capacity> findAllCapacities(int page, int size, String sortBy, String sortOrder);

    Flux<CapacityBootcamp> createCapacitiesBootcamp(List<CapacityBootcamp> capacityBootcamps);
}
