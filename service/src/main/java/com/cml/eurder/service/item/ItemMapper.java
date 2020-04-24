package com.cml.eurder.service.item;

import com.cml.eurder.domain.item.Item;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.cml.eurder.domain.item.Item.ItemBuilder.itemBuilder;

@Component
public class ItemMapper {
    public Collection<ItemDto> toDto(Collection<Item> itemCollection) {
        return itemCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ItemDto toDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getStockAmount(), item.getPrice()
        , item.isInStock(), item.getShippingDate());
    }

    public Item toItem(ItemDto itemDto) {
        return itemBuilder()
                .withName(itemDto.getName())
                .withDescription(itemDto.getDescription())
                .withPrice(itemDto.getPrice())
                .withStockAmount(itemDto.getStockAmount())
                .build();
    }
}
