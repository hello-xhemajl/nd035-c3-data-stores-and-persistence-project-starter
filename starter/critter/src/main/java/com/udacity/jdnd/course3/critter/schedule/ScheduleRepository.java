package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetsPetId(Long petId);
    List<Schedule> findByEmployeesUserId(Long employeeId);
    List<Schedule> findByPetsOwnerUserId(Long ownerId);
}
