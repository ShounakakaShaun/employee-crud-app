package com.shaunstudies12.gmail.com.SpringProject.repositories;

import com.shaunstudies12.gmail.com.SpringProject.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {


}
