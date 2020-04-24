package com.cml.eurder.domain.item;

import com.cml.eurder.domain.DefaultData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.cml.eurder.domain.item.Item.ItemBuilder.itemBuilder;

class ItemRepositoryTest {
//    ItemRepository itemRepository = new ItemRepository();
//    Item item1 = itemBuilder().withStockAmount(10).build();
//    Item item2 = itemBuilder().withStockAmount(0).withPrice(new Price(700, Currency.EURO)).build();
//
//    @Test
//    void checkIfItemIsAddedToDataBase(){
//        itemRepository.addItem(item1);
//        Assertions.assertTrue(itemRepository.getItemDatabase().containsKey(item1.getId()));
//    }
//    @Test
//    void checkIfShippingDateSetToNextDayWhenItemIsInStock(){
//        itemRepository.addItem(item1);
//        Assertions.assertEquals(LocalDate.now().plusDays(1), item1.getShippingDate());
//    }
//    @Test
//    void checkIfShippingDateSetToNextWeekWhenItemIsNotInStock(){
//        itemRepository.addItem(item2);
//        Assertions.assertEquals(LocalDate.now().plusWeeks(1), item2.getShippingDate());
//    }
//
//    @Test
//    void getItemPrice(){
//        itemRepository.addItem(item2);
//        Assertions.assertEquals(700, itemRepository.getItemById(item2.getId()).getPrice().getPriceAmount());
//    }


}