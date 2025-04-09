package com.onclass.usecase.technology;

import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.CapacityBootcamp;
import com.onclass.model.capacity.gateways.ICapacityPersistencePort;
import com.onclass.model.capacity.gateways.ICapacityServicePort;
import com.onclass.usecase.technology.validations.CapacityCreateValidations;
import com.onclass.usecase.technology.validations.CapacityPageValidations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort persistencePort;
    private final CapacityCreateValidations capacityCreateValidations;
    private final CapacityPageValidations capacityPageValidations;

    public CapacityUseCase(ICapacityPersistencePort persistencePort,
                           CapacityCreateValidations capacityCreateValidations,
                           CapacityPageValidations capacityPageValidations) {
        this.persistencePort = persistencePort;
        this.capacityCreateValidations = capacityCreateValidations;
        this.capacityPageValidations = capacityPageValidations;
    }

    @Override
    public Mono<Capacity> createCapacity(Capacity capacity) {
        return capacityCreateValidations.validateCapacity(capacity)
                .flatMap(persistencePort::saveCapacity);
    }

    @Override
    public Flux<Capacity> getAllCapacities(int page, int size, String sortBy, String sortOrder) {
        return persistencePort.findAllCapacities(size, page, sortBy, sortOrder);

    }

    @Override
    public Flux<CapacityBootcamp> createCapacitiesBootcamp(List<CapacityBootcamp> capacityBootcamps) {
        return persistencePort.createCapacitiesBootcamp(capacityBootcamps);
    }


}
