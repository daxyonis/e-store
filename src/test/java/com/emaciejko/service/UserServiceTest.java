package com.emaciejko.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emaciejko.domain.Cart;
import com.emaciejko.domain.CartDetail;
import com.emaciejko.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  
public class UserServiceTest {

    private UserService userService;
    private ProductService productService;
    
    @Autowired
    public void setUserService(UserService userService){
	this.userService = userService;
    }
    
    @Autowired
    public void setProductService(ProductService productService){
	this.productService = productService;
    }
    
    @Test
    public void saveTest(){
	User user = new User();
	User newUser = userService.save(user);
	assertTrue(newUser.getId() != null);
	assertTrue(newUser.getVersion() != null);
    }
    
    @Test
    public void saveWithCartTest(){
	User user = new User();
	user.setUsername("johndoe");
	user.setPassword("weakpassword");
	
	Cart cart = new Cart();
	user.setCart(cart);
	
	User newUser = userService.save(user);
	
	assertTrue(newUser.getId() != null);
	assertTrue(newUser.getVersion() != null);
	assertTrue(newUser.getCart() != null);
	assertTrue(newUser.getCart().getId() != null);
	assertTrue(newUser.getCart().getVersion() != null);
	
    }
    
    @Test
    public void saveWithCartAndCartDetailsTest(){
	User user = new User();
	user.setUsername("johndoe");
	user.setPassword("weakpassword");
	
	Cart cart = new Cart();
	user.setCart(cart);
	
	productService.findAll().forEach(p -> {
	    CartDetail cd = new CartDetail();
	    cd.setProduct(p);
	    user.getCart().addCartDetail(cd);
	});
	
	User newUser = userService.save(user);
	
	assertTrue(newUser.getId() != null);
	assertTrue(newUser.getVersion() != null);
	assertTrue(newUser.getCart() != null);
	assertTrue(newUser.getCart().getId() != null);
	assertTrue(newUser.getCart().getVersion() != null);
	assertTrue(newUser.getCart().getCartDetails().size() == 4);
	
    }
    
    @Test
    public void deleteWithCartAndCartDetailsTest(){
	User user = new User();
	user.setUsername("johndoe");
	user.setPassword("weakpassword");
	
	Cart cart = new Cart();
	user.setCart(cart);
	
	productService.findAll().forEach(p -> {
	    CartDetail cd = new CartDetail();
	    cd.setProduct(p);
	    user.getCart().addCartDetail(cd);
	});
	
	// First save the user
	User newUser = userService.save(user);
	
	// Then delete it
	userService.delete(newUser.getId());		
	
	User shouldBeNull = userService.find(newUser.getId());
	assertTrue(shouldBeNull == null);
	// TODO : check that Cart and CartDetails have been removed
	
    }
}
