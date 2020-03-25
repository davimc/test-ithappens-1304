package com.ithappens.inventory.controllers;

import com.ithappens.inventory.controllers.dtos.OrderDto;
import com.ithappens.inventory.controllers.forms.ItemForm;
import com.ithappens.inventory.controllers.forms.StockForm;
import com.ithappens.inventory.controllers.forms.OrderForm;
import com.ithappens.inventory.domains.Item;
import com.ithappens.inventory.domains.Order;
import com.ithappens.inventory.repositories.ItemRepository;
import com.ithappens.inventory.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;


    /*@GetMapping
    public List<Order> listOrders(){
        return orderService.listOrders();
    }*/
    @GetMapping(value="/information")
    public OrderDto informations(){
        return orderService.informtationsOrder();
    }
    @GetMapping
    public List<Order> listOrders(){
        return orderService.listOrders();
    }
    @PostMapping("/register")
    public ResponseEntity<OrderForm> registerOrder(@RequestBody OrderForm orderForm, UriComponentsBuilder uriBuilder){

        Order order = orderService.startOrder(orderForm.getBranchLocation(),orderForm.getClientIdentification(),orderForm.getEmployeeIdentification(),orderForm.getType(),orderForm.getObs());
        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderForm(order));

    }
    @PostMapping(value = "/items")
    public ResponseEntity<StockForm> addItem(@RequestBody StockForm stockForm, UriComponentsBuilder uriBuilder){
        try {
            Item item = orderService.addItemOrder(stockForm.getProductDescrition(), stockForm.getProductBarcode(), stockForm.getProductSequential());
            if(item !=null) {

                URI uri = uriBuilder.path("/orders/items/{id}").buildAndExpand(item.getId()).toUri();
                return ResponseEntity.created(uri).body(new StockForm(item));
            }
            else{

                return ResponseEntity.badRequest().body(new StockForm("insufficient stock"));
            }

        }catch(NoSuchElementException e){
            return ResponseEntity.badRequest().body(new StockForm("insufficient stock"));
        }
    }
    @PostMapping(value="/items/remove")
    public ResponseEntity<ItemForm> removeItem(@RequestBody ItemForm itemForm, UriComponentsBuilder uriBuilder){
        try {
            System.out.println("1");
            Item item = orderService.removeItemOrder(itemForm.getItemId());
            System.out.println("2");

            URI uri = uriBuilder.path("/orders/items/{id}").buildAndExpand(item.getId()).toUri();
            System.out.println("3");

            return ResponseEntity.accepted().body(new ItemForm("Item removido"));
        }catch(NoSuchElementException e){
            return ResponseEntity.badRequest().body(new ItemForm("Not Found"));
        }
    }
    @PostMapping(value="/items/increment")
    public ResponseEntity<ItemForm> incrementItem(@RequestBody ItemForm form, UriComponentsBuilder uriBuilder){
        try{
            Item item = orderService.addQuantityItem(form.getItemId(),form.getQuantity());
            URI uri = uriBuilder.path("/orders/items/{id}").buildAndExpand(item.getId()).toUri();
            return ResponseEntity.accepted().body(new ItemForm(item));
        }catch(NoSuchElementException e){
            return ResponseEntity.badRequest().body(new ItemForm());
        }
    }
    @PostMapping(value="/items/decrement")
    public ResponseEntity<ItemForm> decrementItem(@RequestBody ItemForm form, UriComponentsBuilder uriBuilder){

        Item item = orderService.removeQuantityItem(form.getItemId(),form.getQuantity());
        URI uri = uriBuilder.path("/orders/items/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.accepted().body(new ItemForm(item));
    }
    @GetMapping(value="/items")
    public List<Item> listItem(){
        return orderService.listItems();

    }

    @PostMapping(value="/finalize")
    public ResponseEntity<OrderForm> finalizeOrder(@RequestBody OrderForm form, UriComponentsBuilder uriBuilder){
        Order order = orderService.finalizeOrder(form.getPayment());
        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.accepted().body(new OrderForm(order));
    }
}
