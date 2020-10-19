package com.example.Employee.Repo;



import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee.Model.Employee;



@Repository
public interface EmployeeRepo extends JpaRepository<com.example.Employee.Model.Employee, Long>{

	Employee findByJoindateBetween(DayOfWeek firstDayOfWeek, DayOfWeek lastDayOfWeek);

	Employee findByJoindateBetween(LocalDate startdte, LocalDate endte);

}
