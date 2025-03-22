package ru.skypro.StreamAPIhomework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.StreamAPIhomework.exception.EmployeeAlreadyAddedException;
import ru.skypro.StreamAPIhomework.exception.EmployeeNotFoundException;
import ru.skypro.StreamAPIhomework.exception.EmployeeStorageIsFullException;
import ru.skypro.StreamAPIhomework.model.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddEmployeeSuccess() {
        Employee employeeToAdd = new Employee("John", "Doe", 100000, 1);
        Employee addedEmployee = employeeService.add(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartmentId());
        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    void shouldRemoveEmployeeSuccess() {
        int maxSize = employeeService.getMaxEmployeeCountInCompany();
        for (int i = 0; i < maxSize; i++) {
            employeeService.add("John" + i, "Doe" + i, 100000 + i, 1);
        }
        Assertions.assertEquals(maxSize, employeeService.getAllEmployee().size());
        employeeService.remove("John1", "Doe1");
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find("John1", "Doe1"));
    }


    @Test
    void shouldFindEmployeeSuccess() {
        Employee employeeToAdd = new Employee("John", "Doe", 100000, 1);
        Employee addedEmployee = employeeService.add(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartmentId());
        Employee foundEmployee = employeeService.find(addedEmployee.getFirstName(), addedEmployee.getLastName());
        Assertions.assertEquals(addedEmployee, foundEmployee);
    }

    @Test
    void shouldGetAllEmployees() {
        int maxSize = employeeService.getMaxEmployeeCountInCompany();
        for (int i = 0; i < maxSize; i++) {
            employeeService.add("John" + i, "Doe" + i, 100000 + i, 1);
        }
        Assertions.assertEquals(maxSize, employeeService.getAllEmployee().size());
    }

    @Test
    void shouldEmployeeAlreadyAddedException() {
        employeeService.add("John", "Doe", 100000, 1);

        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add("John", "Doe", 100000, 1));
    }

    @Test
    void shouldNotFoundException() {
        int maxSize = employeeService.getMaxEmployeeCountInCompany();
        for (int i = 0; i < maxSize; i++) {
            employeeService.add("John" + i, "Doe" + i, 100000 + i, 1);
        }
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find("John", "Doe"));
    }

    @Test
    void shouldEmployeeStorageIsFullException() {
        int maxSize = employeeService.getMaxEmployeeCountInCompany();
        for (int i = 0; i < maxSize; i++) {
            employeeService.add("John" + i, "Doe" + i, 100000 + i, 1);
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add("John" + maxSize, "Doe" + maxSize, 100000 + maxSize, 1));
    }
}