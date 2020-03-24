package com.ithappens.inventory.controllers.forms;

import com.ithappens.inventory.domains.Item;
import com.ithappens.inventory.repositories.ItemRepository;

public class ItemForm {
    private Long itemId;
    private int quantity;

    public ItemForm() {
    }

    public ItemForm(Long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public ItemForm(Item item) {
        this.itemId = item.getId();
        this.quantity = item.getQuantity();
    }

    public Long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }


}
