package com.narel.online_store.service;

import com.narel.online_store.model.Product;
import com.narel.online_store.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)

public class ProductServiceImplTest {

    @Mock
   private ProductRepository repository;
    @InjectMocks
    private ProductServiceImpl service;

    @Test
    public void createProduct() {
        Product expect = new Product();
        expect.setId(2l);
        expect.setName("Aser2000");
        Product expect2 = new Product();
        expect.setId(1l);
        expect.setName("Aser200044412");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(expect,expect2));
        List<Product> savedList=service.findAll();
        Assert.assertNotNull(service.findAll());
        Assert.assertEquals(2,savedList.size());



    }

    @Test
    public void deleteProduct() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByName() {
    }
}