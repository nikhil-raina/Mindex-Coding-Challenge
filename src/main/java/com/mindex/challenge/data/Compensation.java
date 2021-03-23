package com.mindex.challenge.data;

import java.util.Date;
import java.util.Objects;

public class Compensation {
    private Employee employee;
    private int salary;
    // Not sure what type of dates needs to be implemented here
    private Date effectiveDate;

    public Compensation(Employee employee, int salary, Date effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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
        return getSalary() == comp.salary &&
                getEmployee().equals(comp.employee) &&
                getEffectiveDate().equals(comp.effectiveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee(), getSalary(), getEffectiveDate());
    }
}
