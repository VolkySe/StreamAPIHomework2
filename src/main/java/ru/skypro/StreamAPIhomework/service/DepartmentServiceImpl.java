package ru.skypro.StreamAPIhomework.service;

import org.springframework.stereotype.Service;
import ru.skypro.StreamAPIhomework.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getAllEmployee()
                .stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getAllEmployee()
                .stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesInDepartment(int departmentId) {
        return employeeService.getAllEmployee()
                .stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployee()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
