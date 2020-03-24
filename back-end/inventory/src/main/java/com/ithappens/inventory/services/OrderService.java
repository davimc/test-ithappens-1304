package com.ithappens.inventory.services;

import com.ithappens.inventory.domains.*;
import com.ithappens.inventory.miscs.enums.ItemStatusEnum;
import com.ithappens.inventory.miscs.enums.OrderTypeEnum;
import com.ithappens.inventory.miscs.enums.PaymentFormatEnum;
import com.ithappens.inventory.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private  BranchRepository branchRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private ItemRepository itemRepository;

    private static Order order;

    public Order startOrder(Order order){
        this.order = order;
        return order;
    }
    public List<Item> listItems() {
        return order.getItems();
    }
    public List<Order> listOrders(){
        return orderRepository.findAll();
    }

    public Order startOrder(String branchLocation, String clientIdentification, String employeeIdentification,  OrderTypeEnum type,String obs) {
        //branchRepository.findByLocation(location).isPresent() ? outway = new Order(branchRepository.findByLocation(location).get(), OrderTypeEnum.OUTWAY, obs) : throw new Exception();
        if (this.isPresent(branchLocation, clientIdentification, employeeIdentification)) {
            order = createOrder(branchLocation, clientIdentification, employeeIdentification, type, obs);
            orderRepository.save(order);
        }
        return order;
        System.out.println("ok");
    }





    public Item addItemToOrder(String description, String barcode, String sequential)throws NoSuchElementException{

            Stock stock = stockService.findStockBy(order.getBranch(),description,barcode,sequential);
            Item item;
            if(stockService.haveInStock(stock, 1)) {
                if (hasItemInOrder(stock) == null) {
                    item = new Item(order, stock);

                    order.addItem(item);
                } else {
                    item = hasItemInOrder(stock);
                    if(item.getItemStatus()== ItemStatusEnum.CANCELED) {
                       item.setItemStatus(ItemStatusEnum.ACTIVE);
                       item.setQuantity(1);

                    }
                    else{
                        order.incrimentQuantityItem(item, 1);
                    }

                }
                order.sumTotal(stock.getPrice(),1);
                itemRepository.save(item);
                return item;

            }
            return null;

    }


    public void removeItemToOrder(Item item){
        Item itemOnOder =getItemOfItemsList(item);
        itemOnOder.cancelStatus();
        order.subtractsTotal(item.getStock().getPrice(),item.getQuantity());
        itemRepository.save(itemOnOder);

    }
    public Item addQuantityToItem(Long itemId, int quantity)throws NoSuchElementException{

        Item itemOnOrder = getItemOfItemsList(itemRepository.findById(itemId).get());
        if( itemOnOrder!=null) {
            if (stockService.haveInStock(stockService.findStockBy(order.getBranch(), itemOnOrder.getStock().getProduct()), 1)) {
                System.out.println("ok");
                itemOnOrder.addQuantity(quantity);
                order.sumTotal(itemOnOrder.getStock().getPrice(), quantity);
                itemRepository.save(itemOnOrder);
            }
        }
        return itemOnOrder;
    }

    public Item removeQuantityToItem(Long itemId,int quantity){
        Item itemOnOrder = getItemOfItemsList(itemRepository.findById(itemId).get());
        itemOnOrder.removeQuantity(quantity);
        if(getItemOfItemsList(itemOnOrder).getQuantity()==0)
            removeItemToOrder(itemOnOrder);
        else
            order.subtractsTotal(itemOnOrder.getStock().getPrice(),quantity);
        return itemOnOrder;
    }
    
    public Order finalizeOrder(PaymentFormatEnum paymentFormat) {
        for (Item item : order.getItems()) {
            Stock stock =stockService.findStockBy(item.getStock().getId());
            if(item.processStatus()) {
                if (order.getOrderType() == OrderTypeEnum.OUTWAY) {
                    stock.modifyQuantity(-(item.getQuantity()));
                } else {
                    stock.modifyQuantity(item.getQuantity());

                }
            }
            order.setPayment(paymentFormat);
            orderRepository.save(order);
        }
        return order;
    }

    /*OTHERS*/
    private boolean isPresent(String branchLocation, String clientIdentification, String employeeIdentification){
        return (branchRepository.findByLocation(branchLocation).isPresent()&& clientRepository.findByIdentification(clientIdentification).isPresent()&&employeeRepository.findByIdentification(employeeIdentification).isPresent());
    }
    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract("_, _, _, _, _ -> new")
    private Order createOrder(String branchLocation, String clientIdentification, String employeeIdentification, OrderTypeEnum type, String obs){
        return new Order(branchRepository.findByLocation(branchLocation).get(), clientRepository.findByIdentification(clientIdentification).get(), employeeRepository.findByIdentification(employeeIdentification).get(), type, obs);

    }






    private Item getItemOfItemsList(Item item){
        for (Item orderItem : order.getItems()) {
            if(orderItem.getId().equals(item.getId())) return orderItem;
        }
        return null;
    }
        private Item hasItemInOrder(Stock stock){
         /*Stream<Item> stream = order.getItems().stream().filter(item->item.getStock().equals(stock));
        System.out.println(stream.collect(Collectors.toList()).size());
         return stream.collect(Collectors.toList());*/
        ArrayList<Stock> stocks = new ArrayList<>();
        for (Item item : order.getItems()) {
            System.out.println(item.getStock().getId().equals(stock.getId()));
            if(item.getStock().equals(stock))
                return item;
        }

        return null;
    }
}
