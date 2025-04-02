package com.onclass.config;

import com.onclass.jpa.adapter.CapacityR2DbcAdapter;
import com.onclass.jpa.adapter.port.ICapacityBootcampRepository;
import com.onclass.jpa.adapter.port.ICapacityRepository;
import com.onclass.jpa.helper.ICapacityBootcampEntityMapper;
import com.onclass.jpa.helper.ICapacityEntityMapper;
import com.onclass.model.capacity.gateways.*;
import com.onclass.usecase.technology.CapacityUseCase;
import com.onclass.usecase.technology.validations.CapacityCreateValidations;
import com.onclass.usecase.technology.validations.CapacityPageValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICapacityRepository capacityRepository;
    private final ICapacityBootcampRepository capacityBootcampRepository;
    private final ICapacityEntityMapper capacityEntityMapper;
    private final ICapacityBootcampEntityMapper capacityBootcampEntityMapper;
    @Bean
    public CapacityCreateValidations capacityCreateValidations() {
        return new CapacityCreateValidations();
    }
    @Bean
    public CapacityPageValidations capacityPageValidations() {
        return new CapacityPageValidations();
    }

    @Bean
    public ICapacityPersistencePort capacityPersistencePort() {
        return new CapacityR2DbcAdapter(capacityRepository,capacityBootcampRepository, capacityEntityMapper,  capacityBootcampEntityMapper);
    }
    @Bean
    public ICapacityServicePort capacityServicePort(ICapacityPersistencePort capacityPersistencePort, CapacityCreateValidations capacityCreateValidations,CapacityPageValidations capacityPageValidations) {
        return new CapacityUseCase(capacityPersistencePort,capacityCreateValidations, capacityPageValidations);
    }

}
