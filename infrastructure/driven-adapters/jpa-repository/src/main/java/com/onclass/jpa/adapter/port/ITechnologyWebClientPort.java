package com.onclass.jpa.adapter.port;

import com.onclass.jpa.helper.TechnologyCapacityDto;
import com.onclass.model.capacity.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface ITechnologyWebClientPort {
    Mono<List<Technology>> getTechnologiesByIds(Set<Integer> ids);

    Mono<Void> associateTechnologyWithCapacity(TechnologyCapacityDto technologyCapacityDto);

    Mono<List<Technology>> getRelationshipsById(Integer capacityId);
}
