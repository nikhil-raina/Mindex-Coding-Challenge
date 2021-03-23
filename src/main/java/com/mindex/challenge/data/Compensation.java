package com.mindex.challenge.data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Compensation {
    private Employee employee;
    private int salary;

    // Not sure what type of dates needs to be implemented here
    // storing UTC time
    private LocalDateTime effectiveDate;

    public Compensation(Employee employee, int salary, LocalDateTime effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        // Default is EST for data consistency
        ZonedDateTime thisZone = effectiveDate.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of("UTC"));
        this.effectiveDate = thisZone.toLocalDateTime();
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "employee=" + getEmployee() +
                ", salary=" + getSalary() +
                ", effectiveDate=" + getEffectiveDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Compensation comp = (Compensation) o;
        return getSalary() == comp.getSalary() &&
                getEmployee().equals(comp.getEmployee()) &&
                getEffectiveDate().equals(comp.getEffectiveDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee(), getSalary(), getEffectiveDate());
    }
}
