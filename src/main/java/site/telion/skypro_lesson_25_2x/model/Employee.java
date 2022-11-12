package site.telion.skypro_lesson_25_2x.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
public class Employee {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private Integer department;
    private Double salary;

    public Employee(String firstName, String lastName) {
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
        this.fullName = firstName + " " + lastName;
    }

    public Employee(String firstName, String lastName, Integer department, Double salary) {
        this(firstName, lastName);
        this.department = department;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder().append(firstName, employee.firstName).append(lastName, employee.lastName).append(fullName, employee.fullName).append(department, employee.department).append(salary, employee.salary).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(firstName).append(lastName).append(fullName).append(department).append(salary).toHashCode();
    }
}
