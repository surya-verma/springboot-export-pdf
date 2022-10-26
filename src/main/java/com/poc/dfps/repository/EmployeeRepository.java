package com.poc.dfps.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.dfps.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}