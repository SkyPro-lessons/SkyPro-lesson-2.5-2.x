package site.telion.skypro_lesson_25_2x.service;

import org.springframework.stereotype.Service;
import site.telion.skypro_lesson_25_2x.exception.EmployeeAlreadyAddedException;
import site.telion.skypro_lesson_25_2x.exception.EmployeeNotFoundException;
import site.telion.skypro_lesson_25_2x.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник есть в базе");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
        }
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с такими данными не найден");
    }

    @Override
    public List<Employee> printList() {
        return Collections.unmodifiableList(employeeList);
    }
}
