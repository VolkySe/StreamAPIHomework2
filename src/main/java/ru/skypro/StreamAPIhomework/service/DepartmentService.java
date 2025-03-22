package ru.skypro.StreamAPIhomework.service;

import ru.skypro.StreamAPIhomework.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    int getEmployeeSumSalary(int departmentId);

    List<Employee> getAllEmployeesInDepartment(int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment();
}
