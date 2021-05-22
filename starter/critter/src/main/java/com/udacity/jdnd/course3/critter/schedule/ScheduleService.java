package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        // Find pets
        List<Pet> pets = petService.findPetsByIds(petIds);

        // Find employees
        List<Employee> employees = employeeService.findEmployees(employeeIds);

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
}
