package ru.skypro.StreamAPIhomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.StreamAPIhomework.model.Employee;
import ru.skypro.StreamAPIhomework.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}/employees")
    public List<Employee> getAllEmployeesInDepartment(@PathVariable int departmentId) {
        return departmentService.getAllEmployeesInDepartment(departmentId);
    }

    @GetMapping("{departmentId}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("{departmentId}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("{departmentId}/salary/sum")
    public int getEmployeeSumSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeSumSalary(departmentId);
    }


    @GetMapping("employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }

}
