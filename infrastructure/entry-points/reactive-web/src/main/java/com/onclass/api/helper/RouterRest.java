package com.onclass.api.helper;


import com.onclass.api.helper.request.CapacityHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterRest {

    @Bean()
    @AddRouterRestInfo
    public RouterFunction<ServerResponse> routerFunction(CapacityHandlerImpl handler) {

        return RouterFunctions.nest(
                path("/capacity"),
                RouterFunctions
                        .route(POST("/capacities"), handler::createCapacity)
                        .andRoute(GET("/capacityList"), handler::getCapacities)
                        .andRoute(POST("/capacityBootcamp"), handler::createCapacitiesBootcamp));
    }

}
