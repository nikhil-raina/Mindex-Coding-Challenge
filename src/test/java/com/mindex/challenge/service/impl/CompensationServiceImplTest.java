package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationCreateUrl;
    private String compensationReadUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee emp1;
    private Employee emp2;
    private Compensation compensation1;

    @Before
    public void setup() {
        String dateTime = "2020-03-15 05:30:00";
        this.compensationCreateUrl = "http://localhost:" + this.port + "/employee/compensation";
        this.compensationReadUrl = "http://localhost:" + this.port + "/employee/compensation/{id}";

        this.emp1 = this.employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        this.emp2 = this.employeeRepository.findByEmployeeId("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        this.compensation1 = new Compensation();
        this.compensation1.setEmployee(this.emp1);
        this.compensation1.setSalary(1000);
        this.compensation1.setEffectiveDate(dateTime);
    }

    @Test
    public void testCreateCompensationReturnNotNull() {
        Compensation compensation = restTemplate.postForEntity(this.compensationCreateUrl, this.compensation1, Compensation.class).getBody();
        assertNotNull(compensation);
    }

    @Test
    public void testCreateCompensationReturnsCompensationObj() {

        String dateTime = "2020-03-15 05:30:00";
        Compensation compensation = new Compensation();
        Employee emp = this.employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        compensation.setEmployee(emp);
        compensation.setSalary(10000);
        compensation.setEffectiveDate(dateTime);
        Compensation response = restTemplate.postForEntity(this.compensationCreateUrl, compensation, Compensation.class).getBody();
        assertEquals(compensation, response);
    }

    @Test
    public void testReadCompensationReturnsCompensationObj() {
        Compensation compensation = restTemplate.getForEntity(this.compensationReadUrl, Compensation.class, this.emp2.getEmployeeId()).getBody();
        assertEquals(compensation.getEmployee().getEmployeeId(), this.emp2.getEmployeeId());
    }

    @Test
    public void testReadCompensationReturnsNullIfNoCompensationPresent() {
        Employee emp = new Employee();
        emp.setEmployeeId("test");

        Compensation compensation = restTemplate.getForEntity(this.compensationReadUrl, Compensation.class, this.emp1.getEmployeeId()).getBody();
        assertNotEquals(emp.getEmployeeId(), compensation.getEmployee().getEmployeeId());
    }

    @After
    public void teardown() {
        this.compensationCreateUrl = null;
        this.compensationReadUrl = null;
        this.compensation1 = null;
        this.emp1 = null;
        this.emp2 = null;
    }

}
