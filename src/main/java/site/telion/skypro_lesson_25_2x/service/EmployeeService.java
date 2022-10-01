package site.telion.skypro_lesson_25_2x.service;

import site.telion.skypro_lesson_25_2x.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> printList();
}
