package com.udacity.jdnd.course3.critter.schedule.exception;

import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeSkill;

import java.util.Set;

public class ActivityNotProvidedForSchedule extends ScheduleException {
    public ActivityNotProvidedForSchedule(Set<EmployeeSkill> notProvidedActivities) {
        super("Activity not provided. The following activities are not provided by the selected employees: " + notProvidedActivities);
    }
}
