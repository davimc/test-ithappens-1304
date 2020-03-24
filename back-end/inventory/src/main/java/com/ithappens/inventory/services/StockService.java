package com.ithappens.inventory.services;

import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Product;
import com.ithappens.inventory.domains.Stock;
import com.ithappens.inventory.repositories.BranchRepository;
import com.ithappens.inventory.repositories.ProductRepository;
import com.ithappens.inventory.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchRepository branchRepository;

    /*public Stock findStock(String description, String barcode, String sequential, String location){
        return stockRepository.findByBranchAndProduct(branchRepository.findByLocation(location).get(),productRepository.findByDescriptionOrBarcodeOrSequential(description,barcode,sequential).get()).get();
    }
    public Stock findStock( Branch branch){
        stockRepository.findByBranchAndProduct(branch, );
    }*/
    public Stock findStockBy(Branch branch, String description, String barcode, String sequential) throws NoSuchElementException{
        try {
            Optional<Stock> stock = stockRepository.findByBranchAndProduct(branch, productRepository.findByDescriptionOrBarcodeOrSequential(description, barcode, sequential).get());
            if (stock.isPresent()) {
                return stock.get();
            }
            return null;
        }catch(NullPointerException e) {
            throw new NoSuchElementException("this item is out of stock");
        }
    }
    public Stock findStockBy(Branch branch, Product product){
        return stockRepository.findByBranchAndProduct(branch,product).get();

    }
    public boolean haveInStock(Stock stock, int quantity){
        return stock.getQuantity()>=quantity;
    }
    public Stock findStockBy(Long id){
        return stockRepository.findById(id).get();
    }

    public void createStock(Branch branch, String description, String barcode, String sequential, int quantity,double price){
        Product p = new Product(description,barcode,sequential);
        productRepository.save(p);
        stockRepository.save(new Stock(branch,p,quantity,price));
    }
}
