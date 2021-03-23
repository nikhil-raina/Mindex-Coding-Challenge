package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureTest {

    private ReportingStructure CuT;
    private Employee emp1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        this.emp1 = this.employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        this.CuT = new ReportingStructure(emp1, 10);
    }

    @Test
    public void testCreatedReportingStructureObj() {
        assertNotNull(this.CuT);
    }

    @Test
    public void testGetCorrectEmployees() {
        Employee emp = this.CuT.getEmployee();
        assertEquals(emp, emp1);
    }

    @Test
    public void testSetNewEmployee() {
        Employee emp2 = this.employeeRepository.findByEmployeeId("test-1-ID");
        this.CuT.setEmployee(emp2);
        assertEquals(this.CuT.getEmployee(), emp2);
    }

    @Test
    public void testGetCorrectNumberOfReports() {
        int numberOfReports = 10;
        assertEquals(this.CuT.getNumberOfReports(), numberOfReports);
    }

    @Test
    public void testSetCorrectNumberOfEmployees() {
        int newNumberOfEmployees = 11;
        this.CuT.setNumberOfEmployees(newNumberOfEmployees);
        assertEquals(this.CuT.getNumberOfReports(), newNumberOfEmployees);
    }

    @Test
    public void testSameReportingStructuresObjAreEqual() {
        Employee emp2 = this.employeeRepository.findByEmployeeId("test-1-ID");
        ReportingStructure rs = new ReportingStructure(emp2, 10);
        assertFalse(this.CuT.equals(rs));
    }

    @Test
    public void testEqualsWithDifferentEmployeesButSameNumberOfReportsWouldBeNotEqual() {
        ReportingStructure rs = new ReportingStructure(emp1, 10);
        assertTrue(this.CuT.equals(rs));
    }


    @Test
    public void testCurrentReportingStructureObjIsEqualToItself() {
        assertTrue(this.CuT.equals(this.CuT));
    }

    @Test
    public void testNullIsNotEqualToReportingObjectClass() {
        assertFalse(this.CuT.equals(null));
    }

    @Test
    public void testToStringSendsTheCorrectString() {
        String response = "ReportingStructure {" +
                "employee=" + this.emp1 +
                ", numberOfEmployees=" + this.CuT.getNumberOfReports() +
                '}';
        assertEquals(response, this.CuT.toString());
    }

    @After
    public void teardown() {
        this.emp1 = null;
        this.CuT = null;
    }
}
