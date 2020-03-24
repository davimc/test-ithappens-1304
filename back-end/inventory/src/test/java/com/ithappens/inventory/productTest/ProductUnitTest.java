package com.ithappens.inventory.productTest;


import com.ithappens.inventory.InventoryApplication;
import com.ithappens.inventory.domains.Product;
import com.ithappens.inventory.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductUnitTest {

    @Autowired
    private ProductRepository productRepository;

    private Validator validator;
    private Product product;
    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        product = new Product("rice", "1265468","1c5754fd");
    }

    private String getViolation(Set<ConstraintViolation<Product>> restrictions){
        return restrictions.iterator().next().getPropertyPath().iterator().next().getName();
    }
    @Test
    public void testSaveProduct(){
        productRepository.save(product);
        assertThat(productRepository.findById(product.getId()).get().getBarcode(),is(product.getBarcode()));
    }
    @Test
    public void testSaveProductWithoutBarcodMustReturnException(){
        product.setBarcode("");
        Set<ConstraintViolation<Product>> restrictions = validator.validate(product);

        assertThat(restrictions,hasSize(1));
        assertThat(getViolation(restrictions),is("barcode"));
    }
    @Test
    public void testSaveProductWithoutSequentialMustReturnException(){
        product.setSequential("");
        Set<ConstraintViolation<Product>> restrictions = validator.validate(product);

        assertThat(restrictions,hasSize(1));
        assertThat(getViolation(restrictions),is("sequential"));
    }

    @Test
    public void testSearchByDescripton(){
        Assert.assertEquals(productRepository.findByDescriptionOrBarcodeOrSequential("soups","","").get().getId(),productRepository.findById(1L).get().getId());
    }
    @Test
    public void testSearchByBarcode(){
        Product product = productRepository.findById(1L).get();
        Product pBarcode = productRepository.findByDescriptionOrBarcodeOrSequential("", product.getBarcode(),"").get();
        System.out.println(pBarcode.getDescription());
        assertThat(product,is(pBarcode));
    }
    @Test
    public void testSearchByDescription(){
        Product product = productRepository.findById(1L).get();
        Product pBarcode = productRepository.findByDescriptionOrBarcodeOrSequential(product.getDescription(), "","").get();
        System.out.println(pBarcode.getDescription());
        assertThat(product,is(pBarcode));
    }
}
