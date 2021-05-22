package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleDTOMapper;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule unsavedSchedule = ScheduleDTOMapper.mapToSchedule(scheduleDTO);

        Schedule schedule = scheduleService.saveSchedule(
                unsavedSchedule,
                scheduleDTO.getPetIds(),
                scheduleDTO.getEmployeeIds()
        );

        return ScheduleDTOMapper.mapToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAllSchedules().stream()
                .map(schedule -> ScheduleDTOMapper.mapToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findScheduleForPet(petId).stream()
                .map(schedule -> ScheduleDTOMapper.mapToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findScheduleForEmployee(employeeId).stream()
                .map(schedule -> ScheduleDTOMapper.mapToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findScheduleForCustomer(customerId).stream()
                .map(schedule -> ScheduleDTOMapper.mapToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }
}
