package com.onclass.api.helper.request;

import com.onclass.api.helper.ICapacityHandler;
import com.onclass.api.helper.mappers.ICapacityRequestMapper;
import com.onclass.api.helper.mappers.ICapacityResponseMapper;
import com.onclass.api.helper.request.dto.CapacityBootcampRequestDto;
import com.onclass.api.helper.request.dto.CapacityRequestDto;
import com.onclass.jpa.adapter.port.ITechnologyWebClientPort;
import com.onclass.jpa.helper.TechnologyCapacityDto;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.CapacityBootcamp;
import com.onclass.model.capacity.Technology;
import com.onclass.model.capacity.gateways.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CapacityHandlerImpl implements ICapacityHandler {

    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;
    private final ITechnologyWebClientPort technologyWebClientPort;


    @Override
    public Mono<ServerResponse> createCapacity(ServerRequest request) {
        return request.bodyToMono(CapacityRequestDto.class)
                .flatMap(dto -> {
                    Capacity capacity = capacityRequestMapper.toDomain(dto);
                    return capacityServicePort.createCapacity(capacity)
                            .flatMap(savedCapacity -> ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(capacityResponseMapper.toDto(savedCapacity)));
                });
    }

    /*private Mono<Void> associateTechnologiesWithCapacity(TechnologyCapacityDto technologyCapacityDto) {
        return technologyWebClientPort.associateTechnologyWithCapacity(technologyCapacityDto);
    }*/

    @Override
    public Mono<ServerResponse> getCapacities(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("0"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        String sortBy = request.queryParam("sortBy").orElse("name");
        String sortOrder = request.queryParam("sortOrder").orElse("asc");

        return capacityServicePort.getAllCapacities(page, size, sortBy, sortOrder)
                .map(capacityResponseMapper::toDto)
                .collectList()
                .flatMap(capacityList -> {
                    return Flux.fromIterable(capacityList)
                            .flatMap(capacity ->
                                    technologyWebClientPort.getRelationshipsById(capacity.getId())
                                            .map(technologies -> {
                                                Set<Technology> technologySet = new HashSet<>(technologies);
                                                capacity.setTechnologyIds(technologySet);

                                                return capacity;
                                            })
                            )
                            .collectList();
                })
                .flatMap(capacitiesWithTechnologies ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(capacitiesWithTechnologies)
                );
    }

    @Override
    public Mono<ServerResponse> createCapacitiesBootcamp(ServerRequest request) {
        return request.bodyToMono(CapacityBootcampRequestDto.class)
                .flatMap(dto -> {
                    Integer idBootcamp = dto.getIdBootcamp();
                    List<Integer> capacityIds = dto.getCapacityIds();

                    List<CapacityBootcamp> capacityBootcamps = capacityIds.stream()
                            .map(idCapacity -> new CapacityBootcamp(null, idCapacity, idBootcamp))
                            .toList();

                    return capacityServicePort.createCapacitiesBootcamp(capacityBootcamps)
                            .then(ServerResponse.status(HttpStatus.CREATED).build());
                });
    }

}
