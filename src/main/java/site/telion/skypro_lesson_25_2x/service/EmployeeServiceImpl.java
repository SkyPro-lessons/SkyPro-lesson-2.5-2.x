package site.telion.skypro_lesson_25_2x.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import site.telion.skypro_lesson_25_2x.exception.EmployeeAlreadyAddedException;
import site.telion.skypro_lesson_25_2x.exception.EmployeeNotFoundException;
import site.telion.skypro_lesson_25_2x.exception.EmptyValueException;
import site.telion.skypro_lesson_25_2x.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, Integer department, Double salary) {
        if (StringUtils.isAnyEmpty(firstName, lastName) || department == null || salary == null) {
            throw new EmptyValueException("Передано пустое значение");
        }
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник есть в базе");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (StringUtils.isAnyEmpty(firstName, lastName)) {
            return null; // todo: возвращать ошибку?
        }
        Employee employee = new Employee(firstName, lastName);
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        if (StringUtils.isAnyEmpty(firstName, lastName)) {
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
        return new ArrayList<>(employees.values());
    }
}
