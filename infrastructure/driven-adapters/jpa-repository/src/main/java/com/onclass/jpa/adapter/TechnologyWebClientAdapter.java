package com.onclass.jpa.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.onclass.jpa.adapter.port.ITechnologyWebClientPort;
import com.onclass.jpa.helper.TechnologyCapacityDto;
import com.onclass.model.capacity.Capacity;
import com.onclass.model.capacity.Technology;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class TechnologyWebClientAdapter implements ITechnologyWebClientPort {

    private final WebClient webClient;

    public TechnologyWebClientAdapter(WebClient.Builder webClientBuilder) {
        //this.webClient = webClientBuilder.baseUrl("http://localhost:8085/technology").build();
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085").build();
    }

    public Mono<List<Technology>> getTechnologiesByIds(Set<Integer> ids) {
        return Flux.fromIterable(ids)
                .flatMap(id -> webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/findById/{id}")
                                .build(id))
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .map(jsonNode -> new Technology(
                                jsonNode.has("id") ? jsonNode.get("id").asInt() : null,
                                jsonNode.has("name") ? jsonNode.get("name").asText() : null
                        ))
                        .onErrorResume(e -> {
                            log.error("Error fetching technology by ID: {}", id, e);
                            return Mono.empty();
                        })
                )
                .filter(technology -> technology.getId() != null)
                .collectList()
                .onErrorResume(e -> {
                    log.error("Error fetching technologies for IDs: {}", ids, e);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> createTechnologies(Capacity capacity) {
        return webClient.post()
                .uri("/technologies")
                .bodyValue(capacity)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> {
                    log.error("Error associating technologies with capacity: ", e);
                    return Mono.empty();
                });
    }

   /* @Override
    public Mono<Void> associateTechnologyWithCapacity(TechnologyCapacityDto technologyCapacityDto) {
        return webClient.post()
                .uri("/associate")
                .bodyValue(technologyCapacityDto)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> {
                    log.error("Error associating technologies with capacity: ", e);
                    return Mono.empty();
                });
    }*/

    @Override
    public Mono<List<Technology>> getRelationshipsById(Integer capacityId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/relationships")
                        .queryParam("capacityId", capacityId)
                        .build())
                .retrieve()
                .bodyToFlux(Technology.class)
                .collectList();

    }


}
