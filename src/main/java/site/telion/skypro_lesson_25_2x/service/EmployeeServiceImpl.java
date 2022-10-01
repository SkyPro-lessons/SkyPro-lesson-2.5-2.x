package site.telion.skypro_lesson_25_2x.service;

import org.springframework.stereotype.Service;
import site.telion.skypro_lesson_25_2x.exception.EmployeeAlreadyAddedException;
import site.telion.skypro_lesson_25_2x.exception.EmployeeNotFoundException;
import site.telion.skypro_lesson_25_2x.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник есть в базе");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
        }
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с такими данными не найден");
    }

    @Override
    public List<Employee> printList() {
        return new ArrayList<Employee>(employees.values());
    }
}
