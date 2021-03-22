package com.mindex.challenge.data;

import java.util.Objects;

public class ReportingStructure {
    private Employee employee;
    private int numberOfEmployees;

    public ReportingStructure(Employee employee, int numberOfEmployees) {
        this.employee = employee;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportingStructure repoStruct = (ReportingStructure) o;
        return getNumberOfEmployees() == repoStruct.getNumberOfEmployees() &&
                getEmployee().equals(repoStruct.getEmployee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee(), getNumberOfEmployees());
    }

    @Override
    public String toString() {
        return "ReportingStructure {" +
                "employee=" + getEmployee() +
                ", numberOfEmployees=" + getNumberOfEmployees() +
                '}';
    }
}
