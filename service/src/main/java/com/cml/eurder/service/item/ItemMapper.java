package com.cml.eurder.service.item;

import com.cml.eurder.domain.item.CurrencyRepository;
import com.cml.eurder.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    @Autowired
    CurrencyRepository currencyRepository;

    public Collection<ItemDto> toDto(Collection<Item> itemCollection) {
        return itemCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ItemDto toDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getStockAmount(), item.getPrice()
        , item.getCurrency(), item.getShippingDate(), item.isInStock());
    }

    public Item toItem(CreateItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getStockAmount(), itemDto.getPrice(),
                itemDto.getCurrency());
    }

    public Item toItem(ItemDto itemDto) {
        return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getStockAmount(), itemDto.getPrice(),
                itemDto.getCurrency());
    }
}
