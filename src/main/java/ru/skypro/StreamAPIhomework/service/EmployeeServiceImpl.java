package ru.skypro.StreamAPIhomework.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.skypro.StreamAPIhomework.exception.EmployeeAlreadyAddedException;
import ru.skypro.StreamAPIhomework.exception.EmployeeNotFoundException;
import ru.skypro.StreamAPIhomework.exception.EmployeeStorageIsFullException;
import ru.skypro.StreamAPIhomework.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Integer maxEmpoyeeCountInCompany = 10;

    private Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void initEmployees() {
        add("Ivan1", "Ivanov1", 1_000_000, 1);
        add("Ivan2", "Ivanov2", 500_000, 1);
        add("Ivan3", "Ivanov3", 400_000, 1);
        add("Ivan4", "Ivanov4", 600_000, 1);
        add("Ivan5", "Ivanov5", 200_000, 1);
        add("Ivan6", "Ivanov6", 300_000, 1);
        add("Ivan7", "Ivanov7", 800_000, 2);
        add("Ivan8", "Ivanov8", 700_000, 2);
        add("Ivan9", "Ivanov9", 900_000, 2);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        String key = getKey(firstName, lastName);
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        } else if (employees.size() >= maxEmpoyeeCountInCompany) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(firstName, lastName))) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(firstName, lastName))) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> getAllEmployee() {
        return employees.values();
    }
}
