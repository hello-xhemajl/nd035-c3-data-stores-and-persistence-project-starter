package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);

        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public Employee findEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Employee saveEmployee(Employee unsavedEmployee) {
        return employeeRepository.save(unsavedEmployee);
    }

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return employeeRepository.findByDayOfWeek(dayOfWeek).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
