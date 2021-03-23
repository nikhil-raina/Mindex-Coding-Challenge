package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Compensation {

    @JsonSerialize
    private Employee employee;
    private int salary;

    // Not sure what type of dates needs to be implemented here
    // storing UTC time
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveDate;

    public Compensation(){ }

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

    public void setEffectiveDate(String effectiveDate) {
        // Default is EST for data consistency
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.effectiveDate = LocalDateTime.parse(effectiveDate, formatter);
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
