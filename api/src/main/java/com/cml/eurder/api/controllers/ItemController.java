package com.cml.eurder.api.controllers;

import com.cml.eurder.service.item.CreateItemDto;
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

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all items", notes = "A list of items will be returned", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> getAllItems() {
        logger.info("Returning all items");
        return itemService.getAllItemsInTheDataBase();
    }

    @PreAuthorize("hasAuthority('ADD_ITEM')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add item", notes = "An item will be added to the items database", response = ItemDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody CreateItemDto itemDto) {
        logger.info("Adding a new item");
        return itemService.addItem(itemDto);
    }

    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    @PutMapping(path = "{ID}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update item", notes = "The item will be updated", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestBody CreateItemDto itemDto, @PathVariable("ID") long id) {
        logger.info("Updating an item");
        return itemService.updateItem(itemDto, id);
    }

    @PreAuthorize("hasAuthority('DELETE_ITEM')")
    @DeleteMapping(path = "{ID}", produces = "application/json")
    @ApiOperation(value = "Delete item", notes = "The item will be deleted", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable("ID") long id) {
        logger.info("Deleting an item");
        itemService.deleteItemById(id);
    }

    @PreAuthorize("hasAuthority('DELETE_ITEM')")
    @DeleteMapping(produces = "application/json")
    @ApiOperation(value = "Delete all items", notes = "All items will be deleted", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllItems() {
        logger.info("Deleting all items");
        itemService.deleteAllItems();
    }
}
