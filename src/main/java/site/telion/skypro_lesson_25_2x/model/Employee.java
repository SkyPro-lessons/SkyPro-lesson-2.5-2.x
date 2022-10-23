package site.telion.skypro_lesson_25_2x.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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

}
