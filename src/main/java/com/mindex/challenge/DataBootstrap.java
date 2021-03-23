package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DataBootstrap {
    private static final String DATASTORE_LOCATION = "/static/employee_database.json";
    private static final String DATASTORE_LOCATION_CUSTOM = "/static/custom_database.json";
    private static final String DATASTORE_LOCATION_CUSTOM_COMPENSATION = "/static/custom_database_compensation.json";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
//        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION);
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION_CUSTOM);
        InputStream inputStreamCompensation = this.getClass().getResourceAsStream(DATASTORE_LOCATION_CUSTOM_COMPENSATION);

        Employee[] employees = null;
        Compensation[] compensations = null;

        try {
            compensations = this.objectMapper.readValue(inputStreamCompensation, Compensation[].class);
            employees = objectMapper.readValue(inputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Compensation compensation: compensations) {
            compensationRepository.insert(compensation);
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }
    }
}
