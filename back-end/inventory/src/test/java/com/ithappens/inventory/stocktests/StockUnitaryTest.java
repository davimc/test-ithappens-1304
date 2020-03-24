package com.ithappens.inventory.stocktests;

import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Product;
import com.ithappens.inventory.domains.Stock;
import com.ithappens.inventory.repositories.BranchRepository;
import com.ithappens.inventory.repositories.ProductRepository;
import com.ithappens.inventory.repositories.StockRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockUnitaryTest {

    private Product p;
    private Branch b;
    private Stock s;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ProductRepository productRepository;
    @Before
    public void start(){
        p = new Product("Arroz", "7770654","1s56t7hf4");
        b = new Branch("Aririzal");
        s = new Stock(b,p,3,2.4);
        productRepository.save(p);
        branchRepository.save(b);
        stockRepository.save(s);
    }
    @After
    public void end(){
        productRepository.delete(p);
        branchRepository.delete(b);
        stockRepository.delete(s);
    }
    @Test
    public void testFindStockByBranchAndProduct(){
        System.out.println(stockRepository.findByBranchAndProduct(b,p).get().getProduct().getDescription());

    }
}
