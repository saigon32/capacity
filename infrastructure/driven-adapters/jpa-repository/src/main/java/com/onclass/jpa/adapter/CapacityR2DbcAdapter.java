package com.onclass.jpa.adapter;

import com.onclass.jpa.adapter.port.ICapacityBootcampRepository;
import com.onclass.jpa.adapter.port.ICapacityRepository;
import com.onclass.jpa.adapter.port.ICapacityTechnologyRepository;
import com.onclass.jpa.config.DBErrorMessage;
import com.onclass.jpa.config.DBException;
import com.onclass.jpa.entity.CapacityBootcampEntity;
import com.onclass.jpa.helper.ICapacityBootcampEntityMapper;
import com.onclass.jpa.helper.ICapacityEntityMapper;
import com.onclass.jpa.helper.ICapacityTechnologyEntityMapper;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.CapacityBootcamp;
import com.onclass.model.capacity.gateways.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
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
    private final ICapacityTechnologyRepository technologyRepository;

    @Override
    public Mono<Capacity> saveCapacity(Capacity capacity) {
        return capacityRepository.save(capacityEntityMapper.toEntity(capacity))
                .map(capacityEntityMapper::toModel)
                .doOnSuccess(capacityResponse -> technologyRepository.saveAll(ICapacityTechnologyEntityMapper.MAPPER.toEntityList(
                                capacity.toBuilder().id(capacityResponse.getId()).build()))
                        .subscribe())
                .flatMap(Mono::just)
                .onErrorResume(DuplicateKeyException.class, ex -> Mono.error(new DBException(DBErrorMessage.CAPACITY_ALREADY_EXISTS)));
    }


    @Override
    public Mono<Capacity> findByName(String name) {
        return capacityRepository.findByName(name)
                .flatMap(technologyEntity -> Mono.just(capacityEntityMapper.toModel(technologyEntity)))
                .switchIfEmpty(Mono.error(new DBException(DBErrorMessage.CAPACITY_NOT_FOUND)));
    }

    @Override
    public Flux<CapacityBootcamp> createCapacitiesBootcamp(List<CapacityBootcamp> capacityBootcamps) {
        List<CapacityBootcampEntity> entities = capacityBootcampEntityMapper.toEntityList(capacityBootcamps);
        return Flux.fromIterable(entities)
                .flatMap(capacityBootcampRepository::save)
                .map(capacityBootcampEntityMapper::toModel);
    }

    @Override
    public Flux<Capacity> findAllCapacities(int page, int size, /*String sortBy,*/ String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by("name").descending() : Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return capacityRepository.findAllBy(pageRequest)
                .collectList()
                .doOnNext(l -> {
                    System.out.println("lista : "+l);
                })
                .flatMapMany(Flux::fromIterable)
                // .flatMap(capacityEntity -> Mono.just(capacityEntityMapper.toModel(capacityEntity)))
                .map(capacityEntityMapper::toModel);
        //.onErrorMap(e -> new DBException(DBErrorMessage.UNEXPECTED_EXCEPTION));
    }

}
