package com.mindex.challenge.data;

import java.util.Objects;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee, int numberOfEmployees) {
        this.employee = employee;
        this.numberOfReports = numberOfEmployees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int numberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfReports = numberOfEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportingStructure repoStruct = (ReportingStructure) o;
        return numberOfReports() == repoStruct.numberOfReports() &&
                getEmployee().equals(repoStruct.getEmployee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee(), numberOfReports());
    }

    @Override
    public String toString() {
        return "ReportingStructure {" +
                "employee=" + getEmployee() +
                ", numberOfEmployees=" + numberOfReports() +
                '}';
    }
}
