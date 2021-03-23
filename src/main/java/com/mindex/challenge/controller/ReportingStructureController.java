package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Mapping the GET request to the read method
     * @param id Employee ID
     * @return A ReportingStructure Obj of the employee ID entered
     */
    @GetMapping("/employee/reports/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received reportingStructure read request for employeeId [{}]", id);
        return reportingStructureService.read(id);
    }
}
