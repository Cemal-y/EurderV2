package com.cml.eurder.domain.item;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Currency findById(long aLong);
}
