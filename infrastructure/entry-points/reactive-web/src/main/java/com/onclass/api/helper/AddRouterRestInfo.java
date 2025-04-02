package com.onclass.api.helper;

import com.onclass.api.helper.request.dto.CapacityBootcampRequestDto;
import com.onclass.api.helper.request.dto.CapacityRequestDto;
import com.onclass.model.capacity.Capacity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;

import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                operation =
                @Operation(
                        description = "Add capacities",
                        operationId = "addCapacities",
                        tags = "capacities",
                        requestBody =
                        @RequestBody(
                                description = "Capacity to add",
                                required = true,
                                content = @Content(schema = @Schema(implementation = CapacityRequestDto.class,
                                        requiredProperties = {"name", "description", "technologies"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Add Capacity response",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Capacity.class))
                                        }),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "Bad Request response",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = ErrorResponse.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.POST,
                operation =
                @Operation(
                        description = "Add capacity_bootcamp",
                        operationId = "addCapacityBootcamp",
                        tags = "capacities",
                        requestBody =
                        @RequestBody(
                                description = "Capacity to add with bootcamp",
                                required = true,
                                content = @Content(schema = @Schema(implementation = CapacityBootcampRequestDto.class,
                                        requiredProperties = {"idBootcamp", "idCapacity"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Add Capacity Bootcamp response",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Capacity.class))
                                        }),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "Bad Request response",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = ErrorResponse.class))
                                        })
                        }))

})
public @interface AddRouterRestInfo {
}
