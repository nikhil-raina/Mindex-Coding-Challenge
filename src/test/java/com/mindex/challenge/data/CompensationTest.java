package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationTest {

    private Compensation CuT;
    private Employee emp1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        this.emp1 = this.employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        this.CuT = new Compensation();
    }

    @Test
    public void testCompensationObjGetsCreated() {
        assertNotNull(this.CuT);
    }

    @Test
    public void testGetEmployeeGetsCorrectEmployee() {
        this.CuT.setEmployee(this.emp1);
        Employee emp = this.CuT.getEmployee();
        assertEquals(this.emp1, emp);
    }

    @Test
    public void testGetEmployeeGetsCorrectSalary() {
        int salary = 100000;
        this.CuT.setSalary(salary);
        int result = this.CuT.getSalary();
        assertEquals(salary, result);
    }

    @Test
    public void testGetEmployeeGetsCorrectDate() {
        String dateTime = "2021-03-15 09:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt = LocalDateTime.parse(dateTime, formatter);
        this.CuT.setEffectiveDate(dateTime);
        LocalDateTime result = this.CuT.getEffectiveDate();
        assertEquals(dt, result);
    }

    @Test
    public void testGetsCorrectToStringResponse() {
        String dateTime = "2021-03-15 09:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt = LocalDateTime.parse(dateTime, formatter);
        this.CuT.setEffectiveDate(dateTime);
        this.CuT.setSalary(100000);
        this.CuT.setEmployee(this.emp1);
        String response = this.CuT.toString();
        String expected = "Compensation{" +
                "employee=" + this.emp1 +
                ", salary=" + 100000 +
                ", effectiveDate=" + dt +
                '}';
        assertEquals(expected, response);
    }

    @Test
    public void testEqualsReturnsFalseWhenObjIsNull() {
        Compensation comp = null;
        assertFalse(this.CuT.equals(comp));
    }

    @Test
    public void testEqualsReturnsFalseWhenObjIsNotCompensation() {
        Object obj = new Object();
        assertFalse(this.CuT.equals(obj));
    }

    @Test
    public void testEqualsReturnsTrueWhenObjIsCompensationAndItself() {
        String dateTime = "2021-03-15 09:30:00";
        this.CuT.setEffectiveDate(dateTime);
        this.CuT.setSalary(100000);
        this.CuT.setEmployee(this.emp1);
        Compensation expected = new Compensation();
        expected.setEmployee(this.emp1);
        expected.setEffectiveDate(dateTime);
        expected.setSalary(100000);
        assertTrue(this.CuT.equals(expected));
    }

    @After
    public void teardown() {
        this.emp1 = null;
        this.CuT = null;
    }


}
