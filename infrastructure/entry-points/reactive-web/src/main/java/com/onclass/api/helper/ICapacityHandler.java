package com.onclass.api.helper;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface ICapacityHandler {
    Mono<ServerResponse> createCapacity(ServerRequest request);
    Mono<ServerResponse> getCapacities(ServerRequest request);

    Mono<ServerResponse> createCapacitiesBootcamp(ServerRequest request);
}
