package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        // gets employee from the read method in the EmployeeServiceImpl
        Employee emp = employeeService.read(compensation.getEmployee().getEmployeeId());
        compensation.setEmployee(emp);

        // Gets the normal format of the time entry to change into UTC
        LocalDateTime effectiveDate = compensation.getEffectiveDate();
        compensation.setEffectiveDate(effectiveDate);
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading for compensation of employee with id [{}]", employeeId);
//        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
        Employee emp = employeeService.read(employeeId);
        Compensation compensation = compensationRepository.findByEmployee(emp);
        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        return compensation;
    }
}
