package com.udacity.jdnd.course3.critter.schedule.model;

import java.util.stream.Collectors;

public class ScheduleDTOMapper {
    public static Schedule mapToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());
        return schedule;
    }

    public static ScheduleDTO mapToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(pet -> pet.getPetId())
                .collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(employee -> employee.getUserId())
                .collect(Collectors.toList()));

        return scheduleDTO;
    }
}
