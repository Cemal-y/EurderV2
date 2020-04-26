package com.cml.eurder.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

     @Override
    <S extends Customer> S save(S s);

    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long aLong);
}
