package site.telion.skypro_lesson_25_2x.model;

import lombok.Data;

@Data
public class Employee {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private Integer department;
    private Double salary;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    public Employee(String firstName, String lastName, Integer department, Double salary) {
        this(firstName, lastName);
        this.department = department;
        this.salary = salary;
    }

}
