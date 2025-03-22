package ru.skypro.StreamAPIhomework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.StreamAPIhomework.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> mockEmployees = new HashMap<>() {{
        put("John1Doe1", new Employee("John1", "Doe1", 100_000, 1));
        put("John2Doe2", new Employee("John2", "Doe2", 150_000, 1));
        put("John3Doe3", new Employee("John3", "Doe3", 200_000, 1));
        put("John4Doe4", new Employee("John4", "Doe4", 250_000, 1));
        put("John5Doe5", new Employee("John5", "Doe5", 300_000, 1));
        put("John6Doe6", new Employee("John6", "Doe6", 350_000, 1));
        put("John7Doe7", new Employee("John7", "Doe7", 400_000, 2));
        put("John8Doe8", new Employee("John8", "Doe8", 450_000, 2));
        put("John9Doe9", new Employee("John9", "Doe9", 500_000, 2));
        put("JohnDoe", new Employee("John", "Doe", 550_000, 1));
    }};

    @Test
    void shouldCorrectlyCalculateSalary() {
        //given
        int departmentId = 2;
        int expectedSumSalary = 1_350_000;
        Mockito.when(employeeServiceMock.getAllEmployee()).thenReturn(mockEmployees.values());
        //when
        int actualSumSalary = departmentService.getEmployeeSumSalary(departmentId);
        //then
        Assertions.assertEquals(expectedSumSalary, actualSumSalary);
    }
    @Test
    void shouldReturnEmployeeWithMaxSalary(){
        //given
        int departmentId = 1;
        Employee expectedEmployee = mockEmployees.get("JohnDoe");
        Mockito.when(employeeServiceMock.getAllEmployee()).thenReturn(mockEmployees.values());
        //when
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        //then
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void shouldReturnEmployeeWithMinSalary(){
        //given
        int departmentId = 1;
        Employee expectedEmployee = mockEmployees.get("John1Doe1");
        Mockito.when(employeeServiceMock.getAllEmployee()).thenReturn(mockEmployees.values());
        //when
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        //then
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void shouldCorrectlyReturnEmployeesByDepartmentId(){
        //given
        int departmentId = 2;
        Collection<Employee> expectedEmployees = List.of(new Employee[]{
                mockEmployees.get("John7Doe7"),
                mockEmployees.get("John8Doe8"),
                mockEmployees.get("John9Doe9")});
        Mockito.when(employeeServiceMock.getAllEmployee()).thenReturn(mockEmployees.values());
        //when
        List<Employee> actualEmployees = departmentService.getAllEmployeesInDepartment(2);
        //then
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void shouldCorrectlyGroupEmployeesByDepartmentId(){
        //given
        Mockito.when(employeeServiceMock.getAllEmployee()).thenReturn(mockEmployees.values());
        Map<Integer, List<Employee>> expectedEmployees = employeeServiceMock.getAllEmployee().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
        //when
        Map<Integer, List<Employee>> actualEmployees = departmentService.getAllEmployeesGroupedByDepartment();
        //then
        Assertions.assertEquals(expectedEmployees,actualEmployees);
    }
}
