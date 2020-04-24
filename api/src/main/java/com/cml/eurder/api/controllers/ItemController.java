package com.cml.eurder.api.controllers;

import com.cml.eurder.domain.item.ItemRepository;
import com.cml.eurder.service.item.ItemDto;
import com.cml.eurder.service.item.ItemService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = ItemController.ITEM_RESOURCE_PATH)
public class ItemController {
    public static final String ITEM_RESOURCE_PATH = "/items";
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private ItemService itemService;
    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all items", notes = "A list of items will be returned", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> getAllItems() {
        logger.info("Returning all items");
        return itemService.getAllItemsInTheDataBase();
    }

    @PreAuthorize("hasAuthority('ADD_ITEM')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add item", notes = "An item will be added to the items database", response = ItemDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        logger.info("Adding a new item");
        return itemService.addItem(itemDto);
    }

    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    @PutMapping(path = "/update/{ID}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update item", notes = "The item will be updated", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestBody ItemDto itemDto, @PathVariable("ID") String id) {
        logger.info("Updating an item");
        return itemService.updateItem(itemDto, id);
    }

}
