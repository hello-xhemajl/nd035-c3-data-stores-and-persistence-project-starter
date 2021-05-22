package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.exception.ActivityNotProvidedForSchedule;
import com.udacity.jdnd.course3.critter.schedule.exception.EmployeeNotAvailableForSchedule;
import com.udacity.jdnd.course3.critter.schedule.exception.ScheduleException;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds) throws ScheduleException {
        // Find pets
        List<Pet> pets = petService.findPetsByIds(petIds);

        // Find employees
        List<Employee> employees = employeeService.findEmployees(employeeIds);

        // Are all employees available?
        maybeThrowEmployeeNotAvailableException(schedule, employees);

        // Do they provide the requested activities?
        maybeThrowActivityNotProvidedException(schedule, employees);

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return savedSchedule;
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForPet(long petId) {
        return scheduleRepository.findByPetsPetId(petId);
    }

    public List<Schedule> findScheduleForEmployee(long employeeId) {
        return scheduleRepository.findByEmployeesUserId(employeeId);
    }

    public List<Schedule> findScheduleForCustomer(long customerId) {
        return scheduleRepository.findByPetsOwnerUserId(customerId);
    }

    private void maybeThrowActivityNotProvidedException(Schedule schedule, List<Employee> employees) throws ActivityNotProvidedForSchedule {
        Set<EmployeeSkill> providedActivities = employees.stream()
                .map(employeeSkills -> employeeSkills.getSkills())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Set<EmployeeSkill> notProvidedActivities = new HashSet<>(schedule.getActivities());
        notProvidedActivities.removeAll(providedActivities);

        boolean someActivityNotProvided = !notProvidedActivities.isEmpty();

        if(someActivityNotProvided) {
            throw new ActivityNotProvidedForSchedule(notProvidedActivities);
        }
    }

    private void maybeThrowEmployeeNotAvailableException(Schedule schedule, List<Employee> employees) throws EmployeeNotAvailableForSchedule {
        List<Employee> unavailableEmployees = employees.stream().filter(employee
                -> !employee.getDaysAvailable().contains(schedule.getDate().getDayOfWeek()))
                .collect(Collectors.toList());

        boolean someEmployeeUnavailable = !unavailableEmployees.isEmpty();

        if (someEmployeeUnavailable) {
            throw new EmployeeNotAvailableForSchedule(
                    unavailableEmployees.stream()
                            .map(employee -> employee.getUserId())
                            .collect(Collectors.toList())
            );
        }
    }
}
