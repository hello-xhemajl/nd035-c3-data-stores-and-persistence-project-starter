package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeDTOMapper;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee unsavedEmployee = EmployeeDTOMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(unsavedEmployee);
        return EmployeeDTOMapper.mapToEmployeeDTO(savedEmployee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        return EmployeeDTOMapper.mapToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);
    }



    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.findEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills());
        return employees.stream()
                .map(employee -> EmployeeDTOMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }
}
