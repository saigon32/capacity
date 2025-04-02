package com.onclass.usecase.technology.validations;

import com.onclass.model.capacity.Capacity;
import com.onclass.model.exception.BusinessErrorMessage;
import com.onclass.model.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


import static com.onclass.usecase.technology.Constants.MAX_DESCRIPTION_LENGTH;
import static com.onclass.usecase.technology.Constants.MAX_NAME_LENGTH;
@Slf4j
public class CapacityCreateValidations {

    public Mono<Capacity> validateCapacity(Capacity capacity) {
        return validateName(capacity)
                .then(validateDescription(capacity))
                .then(validateTechnologiesCount(capacity))
                .then(Mono.just(capacity))
                .doOnNext(validatedCapacity -> log.info("Validated Capacity: {}", validatedCapacity));
    }

    private Mono<Void> validateName(Capacity capacity) {
        if (capacity.getName().length() > MAX_NAME_LENGTH) {
            return Mono.error(new BusinessException(BusinessErrorMessage.NAME_CHARACTERS_EXCEED));
        }
        if (capacity.getName().isEmpty()) {
            return Mono.error(new BusinessException(BusinessErrorMessage.MANDATORY_NAME));
        }
        return Mono.empty();
    }

    private Mono<Void> validateDescription(Capacity capacity) {
        if (capacity.getDescription().isEmpty()) {
            return Mono.error(new BusinessException(BusinessErrorMessage.MANDATORY_DESCRIPTION));
        }
        if (capacity.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            return Mono.error(new BusinessException(BusinessErrorMessage.DESCRIPTION_CHARACTERS_EXCEED));
        }
        return Mono.empty();
    }

    private Mono<Void> validateTechnologiesCount(Capacity capacity) {
        int technologyCount = capacity.getTechnologyIds() != null ? capacity.getTechnologyIds().size() : 0;
        if (technologyCount < 3 || technologyCount > 20) {
            return Mono.error(new BusinessException(BusinessErrorMessage.INVALID_TECHNOLOGY_COUNT));
        }
        return Mono.empty();
    }

}
