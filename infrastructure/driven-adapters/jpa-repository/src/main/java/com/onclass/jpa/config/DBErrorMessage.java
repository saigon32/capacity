package com.onclass.jpa.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DBErrorMessage {
    CAPACITY_ALREADY_EXISTS("Capacidad ya existe", 400),
    UNEXPECTED_EXCEPTION("Sucedió un error inesperado", 500),
    NAME_CHARACTERS_EXCEED("The description cannot exceed 50 characters", 404),
    CAPACITY_NOT_FOUND("Capacidad no existe", 404);
    private final String message;
    private final int httpStatusCode;
}
