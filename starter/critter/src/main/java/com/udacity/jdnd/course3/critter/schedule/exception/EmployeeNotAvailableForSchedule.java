package com.udacity.jdnd.course3.critter.schedule.exception;

import java.util.List;

public class EmployeeNotAvailableForSchedule extends ScheduleException {
    public EmployeeNotAvailableForSchedule(List<Long> employeeIds) {
        super("Employees unavailable. The following employs are unavailable: " + employeeIds);
    }
}
