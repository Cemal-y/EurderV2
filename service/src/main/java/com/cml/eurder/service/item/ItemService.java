package com.cml.eurder.service.item;

import com.cml.eurder.domain.exceptions.InputCanNotBeNullException;
import com.cml.eurder.domain.exceptions.ItemNotFoundException;
import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.service.DefaultData;
import com.cml.eurder.service.employee.CreateEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class ItemService {
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;
    private DefaultData defaultData;


    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, DefaultData defaultData) {
      this.itemRepository = itemRepository;
      this.itemMapper = itemMapper;
      this.defaultData = defaultData;
      createDefaultData();
    }

    public Collection<ItemDto> getAllItemsInTheDataBase() {
        return itemMapper.toDto(itemRepository.findAll());
    }

    public ItemDto addItem(CreateItemDto itemDto){
        checkIfInputIsNull(itemDto);
        return itemMapper.toDto(itemRepository.save(itemMapper.toItem(itemDto)));
    }

    public ItemDto updateItem(CreateItemDto itemDto, long id) {
        checkIfInputIsNull(itemDto);
        itemRepository.findAll().stream().filter(item -> item.getId() == id).findAny().orElseThrow(() -> new ItemNotFoundException("id"));
        itemRepository.updateItem(itemDto.getCurrency(), itemDto.getDescription(), itemDto.getName(), itemDto.getPrice(), itemDto.getStockAmount(), id);
        return getItemById(id);
    }

    public ItemDto getItemById(long id) {
        return itemMapper.toDto(itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id")));
    }

    public void deleteItemById(long id){
        checkIfIdExists(id);
        itemRepository.deleteById(id);
    }


    public void deleteAllItems(){
        itemRepository.deleteAll();
    }


    private static <T> void checkIfInputIsNull(T input) {
        if (input == null) {
            throw new InputCanNotBeNullException();
        }
    }

    private void checkIfIdExists(long id){
        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id"));
    }

    private void createDefaultData(){
        for (CreateItemDto itemDto:defaultData.getDefaultItems()){
            this.addItem(itemDto);
        }
    }
}
