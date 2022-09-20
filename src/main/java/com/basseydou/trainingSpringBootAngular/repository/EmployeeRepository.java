package com.basseydou.trainingSpringBootAngular.repository;

import com.basseydou.trainingSpringBootAngular.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee,Long> {

}
