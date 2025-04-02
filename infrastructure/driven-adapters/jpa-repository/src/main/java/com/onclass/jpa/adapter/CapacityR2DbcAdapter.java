package com.onclass.jpa.adapter;

import com.onclass.jpa.adapter.port.ICapacityBootcampRepository;
import com.onclass.jpa.adapter.port.ICapacityRepository;
import com.onclass.jpa.config.DBErrorMessage;
import com.onclass.jpa.config.DBException;
import com.onclass.jpa.entity.CapacityBootcampEntity;
import com.onclass.jpa.entity.CapacityEntity;
import com.onclass.jpa.helper.ICapacityBootcampEntityMapper;
import com.onclass.jpa.helper.ICapacityEntityMapper;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.CapacityBootcamp;
import com.onclass.model.capacity.gateways.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
public class CapacityR2DbcAdapter implements ICapacityPersistencePort {

    private final ICapacityRepository capacityRepository;
    private final ICapacityBootcampRepository capacityBootcampRepository;
    private final ICapacityEntityMapper capacityEntityMapper;
    private final ICapacityBootcampEntityMapper capacityBootcampEntityMapper;

    @Override
    public Mono<Capacity> saveCapacity(Capacity capacity) {

        CapacityEntity entity = capacityEntityMapper.toEntity(capacity);
        return capacityRepository.save(entity)
                .map(capacityEntityMapper::toModel)
                .onErrorResume(DuplicateKeyException.class, ex -> Mono.error(new DBException(DBErrorMessage.CAPACITY_ALREADY_EXISTS)));
    }

    @Override
    public Mono<Capacity> findByName(String name) {
        return capacityRepository.findByName(name)
                .flatMap(technologyEntity -> Mono.just(capacityEntityMapper.toModel(technologyEntity)))
                .switchIfEmpty(Mono.error(new DBException(DBErrorMessage.CAPACITY_NOT_FOUND)));
    }

    @Override
    public Flux<Capacity> findAllCapacities(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by("name").descending() : Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return capacityRepository.findAllBy(pageRequest)
                .flatMap(capacityEntity -> Mono.just(capacityEntityMapper.toModel(capacityEntity)))
                .onErrorMap(e -> new DBException(DBErrorMessage.UNEXPECTED_EXCEPTION));
    }

    @Override
    public Flux<CapacityBootcamp> createCapacitiesBootcamp(List<CapacityBootcamp> capacityBootcamps) {
        List<CapacityBootcampEntity> entities = capacityBootcampEntityMapper.toEntityList(capacityBootcamps);
        return Flux.fromIterable(entities)
                .flatMap(capacityBootcampRepository::save)
                .map(capacityBootcampEntityMapper::toModel);
    }



}
