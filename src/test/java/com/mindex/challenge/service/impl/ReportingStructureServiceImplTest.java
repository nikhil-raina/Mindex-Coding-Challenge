package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee emp1;
    private Employee emp2;

    @Before
    public void setup() {
        this.reportingStructureUrl = "http://localhost:" + this.port + "/employee/reports/{id}";

        this.emp1 = this.employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        this.emp1 = this.employeeRepository.findByEmployeeId("test-2-ID");
    }

    @Test
    public void testHTTPStatusIsOk() {
        ResponseEntity<ReportingStructure> response = restTemplate.getForEntity(this.reportingStructureUrl, ReportingStructure.class, emp1.getEmployeeId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testReportingStructureCreated() {
        ReportingStructure CuT = new ReportingStructure(this.emp1, 10);
        assertNotNull(CuT);
    }

    @Test
    public void testReportingStructureHasCorrectNumberOfReports() {
        ReportingStructure CuT = new ReportingStructure(this.emp1, 10);
        int numberOfReports = 10;
        assertEquals(CuT.numberOfReports(), numberOfReports);
    }

    @Test
    public void testReportStructureHasCorrectEmployee() {
        ReportingStructure CuT = new ReportingStructure(this.emp1, 10);
        String employeeID = emp1.getEmployeeId();
        assertEquals(CuT.getEmployee().getEmployeeId(), employeeID);
    }

    @Test
    public void testReportStructureDoesntCreateIdenticalReportingStructures() {
        ReportingStructure CuT_1 = new ReportingStructure(this.emp1, 10);
        ReportingStructure CuT_2 = new ReportingStructure(this.emp2, 10);
        assertNotEquals(CuT_1, CuT_2);
    }

    @After
    public void teardown() {
        this.reportingStructureUrl = null;
        this.emp1 = null;
        this.emp2 = null;
    }
}
