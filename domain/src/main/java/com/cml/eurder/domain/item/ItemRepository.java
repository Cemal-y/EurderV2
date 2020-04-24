package com.cml.eurder.domain.item;

import com.cml.eurder.domain.DefaultData;
import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.ItemNotFoundException;
import com.cml.eurder.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static com.cml.eurder.domain.item.Currency.EURO;
import static com.cml.eurder.domain.item.Item.ItemBuilder.itemBuilder;

@Repository
public class ItemRepository {
    private ConcurrentHashMap<String, Item> itemDatabase;

    DefaultData defaultData;
    @Autowired
    public ItemRepository(DefaultData defaultData) {
        this.itemDatabase = new ConcurrentHashMap<>();
        this.defaultData = defaultData;
//        this.defaultData = new DefaultData();
        createDefaultData();
    }

    public ConcurrentHashMap<String, Item> getItemDatabase() {
        return itemDatabase;
    }

    public Collection<Item> getAllItems(){
        return itemDatabase.values();
    }

    public Item addItem(Item item){
        checkIfInputNull(item);
//        checkIfItemIsAvailable(item);
        itemDatabase.put(item.getId(), item);
        return item;
    }

    public Item updateItem(Item item, String id){
        checkIfInputNull(item);
        itemDatabase.get(id).setName(item.getName())
                .setPrice(item.getPrice()).setDescription(item.getDescription())
                .setStockAmount(item.getStockAmount());
        return item;
    }

    public Item getItemById(String id){
        return itemDatabase.get(id);
    }




//    private void checkIfItemIsAvailable(Item item) {
//        if (!item.isAvailable()){
//            throw new ItemIsNotAvailableException();
//        }
//    }

    public static <T> void checkIfInputNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    public void createDefaultData(){
//        Item smartphone = itemBuilder().withStockAmount(10).withName("Laptop")
//                .withPrice(new Price(700, EURO)).build();
//        Item laptop = itemBuilder().withStockAmount(10).withName("Smarthone")
//                .withPrice(new Price(700, EURO)).build();
//        itemDatabase.put(smartphone.getId(), smartphone);
//        itemDatabase.put(laptop.getId(), laptop);
        for (Item item : defaultData.getDefaultItems()){
            this.addItem(item);
        }
    }
}
