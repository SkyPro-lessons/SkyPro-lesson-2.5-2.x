package site.telion.skypro_lesson_25_2x.service;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.telion.skypro_lesson_25_2x.exception.EmployeeAlreadyAddedException;
import site.telion.skypro_lesson_25_2x.exception.EmployeeNotFoundException;
import site.telion.skypro_lesson_25_2x.exception.EmptyValueException;
import site.telion.skypro_lesson_25_2x.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static site.telion.skypro_lesson_25_2x.service.EmployeeServiceImplTextConstants.*;

public class EmployeeServiceImplTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(FIRSTNAME_IVAN, LASTNAME_PETROV, DEPARTMENT_ONE, SALARY_10000),
                Arguments.of(FIRSTNAME_OLGA, LASTNAME_SHISHKINA, DEPARTMENT_THREE, SALARY_NEGATIVE_10000),
                Arguments.of(FIRSTNAME_ANTON, LASTNAME_SERGEEV, DEPARTMENT_TWO, SALARY_BIGNUMBER)
        );
    }

    @Test
    public void addingElementThatInMap() throws EmployeeAlreadyAddedException {
        employeeService.add(FIRSTNAME_IVAN, LASTNAME_PETROV, DEPARTMENT_ONE, SALARY_10000);
        Throwable thrown = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(FIRSTNAME_IVAN, LASTNAME_PETROV, DEPARTMENT_ONE, SALARY_10000);
        });
        assertNotNull(thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectAddEntryToMapEmployees(String firstName, String lastName, Integer department, Double salary) {
        Employee expectedEmployee = employeeService.add(firstName, lastName, department, salary);
        Map<String, Employee> expected = employeeService.getEmployees();
        expected.put(expectedEmployee.getFullName(), expectedEmployee);

        Employee actualEmployee = new Employee(firstName, lastName, department, salary);
        Map<String, Employee> actual = new HashMap<>();
        actual.put(actualEmployee.getFullName(), actualEmployee);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectRemoveEntryFromMapEmployees(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        String firstNameTest = firstName + "test";
        String lastNameTest = lastName + "test";
        Employee employeeTest = new Employee(firstNameTest, lastNameTest);

        Map<String, Employee> expected = employeeService.getEmployees();
        expected.put(employee.getFullName(), employee);
        expected.put(employeeTest.getFullName(), employeeTest);
        employeeService.remove(firstName, lastName);

        Map<String, Employee> actual = new HashMap<>();
        actual.put(employeeTest.getFullName(), employeeTest);

        assertEquals(expected, actual);
    }

    @Test
    public void deletingEmployeeThatNotInMap() {
        Map<String, Employee> employees = employeeService.getEmployees();
        Employee employee = employees.remove("Non-existent user");
        assertNull(employee);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectFindEmployeeInMap(String firstName, String lastName) {
        Employee actual = new Employee(firstName, lastName);
        Map<String, Employee> employees = employeeService.getEmployees();
        employees.put(actual.getFullName(), actual);

        Employee expected = employeeService.find(firstName, lastName);
        assertEquals(expected, actual);
    }

    @Test
    public void EmployeeNotFoundInMap() throws EmployeeNotFoundException {
        Throwable thrown = assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("Not", "Found"));
    }

    @Test
    public void shouldCorrectIfEnteredInvalidValues() throws EmptyValueException {
        Throwable thrown = assertThrows(EmptyValueException.class, () -> employeeService.find("111", "Петров"));
    }


    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectPrintListFromValuesMapEmployees(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        Map<String, Employee> employees = employeeService.getEmployees();
        employees.put(employee.getFullName(), employee);

        List<Employee> expected = employeeService.printList();
        List<Employee> actual = new ArrayList<>(List.of(employee));

        assertEquals(expected, actual);
    }

}
