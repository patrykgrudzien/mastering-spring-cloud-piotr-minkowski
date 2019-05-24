package com.jurik99.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.stereotype.Service;

import static com.jurik99.Chapter1Application.PERSON_ADD_METER_NAME;
import static com.jurik99.Chapter1Application.PERSON_DELETE_METER_NAME;

@Service
public class PersonCounterService {

    private final Counter personAddCounter;
    private final Counter personDeleteCounter;

    public PersonCounterService(final MeterRegistry registry) {
        this.personAddCounter = registry.counter(PERSON_ADD_METER_NAME);
        this.personDeleteCounter = registry.counter(PERSON_DELETE_METER_NAME);
    }

    public void countNewPersons() {
        this.personAddCounter.increment();
    }

    public void countDeletedPersons() {
        this.personDeleteCounter.increment();
    }
}
