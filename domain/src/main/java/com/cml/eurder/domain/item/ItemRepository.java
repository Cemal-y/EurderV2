package com.cml.eurder.domain.item;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Override
    Collection<Item> findAll();

//    Item findById(long aLong);

    @Override
    Optional<Item> findById(Long aLong);

    @Override
    <S extends Item> S save(S s);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Item item);



    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Item i set i.currency = :currency, i.description = :description, i.name = :name, i.price = :price, i.stockAmount = :stockAmount where i.id = :id")
    void updateItem(@Param("currency") Currencies item, @Param("description") String description,
                              @Param("name") String name, @Param("price") long price, @Param("stockAmount") int stockAmount, @Param("id") long id);

}
