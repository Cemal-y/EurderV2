package com.cml.eurder.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    <S extends Employee> S save(S s);

    @Override
    List<Employee> findAll();

    @Override
    Optional<Employee> findById(Long aLong);

}
