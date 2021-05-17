package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.user.employee.model.Employee;
import com.udacity.jdnd.course3.critter.user.employee.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e " +
            "WHERE :dayOfWeek MEMBER OF e.daysAvailable " +
            "AND (:skills) MEMBER OF e.skills")
    List<Employee> findByDayOfWeekAndHavingAllSkills(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills);
}
