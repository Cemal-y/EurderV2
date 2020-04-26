package com.cml.eurder.service.item;

import com.cml.eurder.TestApplication;
import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import static com.cml.eurder.domain.item.Currencies.EURO;
import static com.cml.eurder.domain.item.Currencies.US_DOLLARS;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemMapper itemMapper;

    @Test
    void checkIfItemAddded(){
        CreateItemDto createItemDto = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        ItemDto itemDto = itemService.addItem(createItemDto);
        assertThat(itemService.getAllItemsInTheDataBase()).contains(itemDto).hasSize(1);
    }

    @Test
    void checkIfAllItemsAreReturned(){
        CreateItemDto createItemDto1 = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        CreateItemDto createItemDto2 = new CreateItemDto("Laptop", "description", 10, 500, US_DOLLARS);
        CreateItemDto createItemDto3 = new CreateItemDto("Laptop", "description", 10, 500, US_DOLLARS);
        ItemDto itemDto1 = itemService.addItem(createItemDto1);
        ItemDto itemDto2 = itemService.addItem(createItemDto2);
        ItemDto itemDto3 = itemService.addItem(createItemDto3);
        List<ItemDto> itemDtos = List.of(itemDto1, itemDto2, itemDto3);
        assertThat(itemService.getAllItemsInTheDataBase()).containsAll(itemDtos).hasSize(3);
    }

    @Test
    void checkIfItemUpdated(){
        CreateItemDto originalItem = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        CreateItemDto updatedItem = new CreateItemDto("Smartphone", "UPDATED", 10, 500, EURO);
        ItemDto itemDto = itemService.addItem(originalItem);
        itemService.updateItem(updatedItem, itemDto.getId());
        assertEquals(itemService.getItemById(itemDto.getId()).getDescription(), "UPDATED");
    }
    @Test
    void checkIfAnExceptionThrownWhenInvalidIdProvided(){
        CreateItemDto itemToUpdate = new CreateItemDto("Smartphone", "UPDATED", 10, 500, EURO);
        assertThatExceptionOfType(ItemNotFoundException.class).isThrownBy( () ->
                itemService.updateItem(itemToUpdate, 5));
    }
    @Test
    void checkIfAnExceptionThrownWhenInputIsNull(){
        assertThatExceptionOfType(InputCanNotBeNullException.class).isThrownBy( () ->
                itemService.addItem(null));
        assertThatExceptionOfType(InputCanNotBeNullException.class).isThrownBy( () ->
                itemService.updateItem(null, 5));
    }
    @Test
    void checkIfItemIsDeleted(){
        CreateItemDto createItemDto = new CreateItemDto("Laptop", "description", 10, 500, US_DOLLARS);
        ItemDto itemDto = itemService.addItem(createItemDto);
        assertTrue(itemService.getAllItemsInTheDataBase().contains(itemDto));
        itemService.deleteItemById(itemDto.getId());
        assertFalse(itemService.getAllItemsInTheDataBase().contains(itemDto));
    }
    @Test
    void checkIfAnExceptionThrownWhenAnItemDeletedTwice(){
        CreateItemDto createItemDto = new CreateItemDto("Laptop", "description", 10, 500, US_DOLLARS);
        ItemDto itemDto = itemService.addItem(createItemDto);
        itemService.deleteItemById(itemDto.getId());
        assertThatExceptionOfType(ItemNotFoundException.class).isThrownBy( () ->
                itemService.deleteItemById(itemDto.getId()));
    }
}