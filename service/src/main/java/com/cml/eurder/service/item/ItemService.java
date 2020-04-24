package com.cml.eurder.service.item;

import com.cml.eurder.domain.item.Item;
import com.cml.eurder.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
      this.itemRepository = itemRepository;
      this.itemMapper = itemMapper;
    }

    public Collection<ItemDto> getAllItemsInTheDataBase() {
        return itemMapper.toDto(itemRepository.getAllItems());
    }

    public ItemDto addItem(ItemDto itemDto){
        return itemMapper.toDto(itemRepository.addItem(itemMapper.toItem(itemDto)));
    }

    public ItemDto updateItem(ItemDto itemDto, String id) {
        return itemMapper.toDto(itemRepository.updateItem(itemMapper.toItem(itemDto), id));
    }
}
