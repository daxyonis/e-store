package com.emaciejko.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.emaciejko.domain.Cart;
import com.emaciejko.domain.CartDetail;
import com.emaciejko.domain.Customer;
import com.emaciejko.domain.Order;
import com.emaciejko.domain.OrderDetail;
import com.emaciejko.domain.OrderStatus;
import com.emaciejko.domain.Product;
import com.emaciejko.domain.Product.CategoryEnum;
import com.emaciejko.domain.Role;
import com.emaciejko.domain.User;
import com.emaciejko.service.ProductService;
import com.emaciejko.service.RoleService;
import com.emaciejko.service.UserService;

/**
 * This class creates initial data for JPA repositories
 * @author Eva
 *
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
    
    private ProductService prodService;    
    private UserService userService;  
    private RoleService roleService;
    
    @Autowired
    public void setProductService(ProductService productService){
	this.prodService = productService;
    }
    
    @Autowired
    public void setUserService(UserService userService){
	this.userService = userService;
    }
    
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
	initProducts();
	initUsersAndCustomers();
	initCarts();
        initOrderHistory();
        initRoles();
        assignUsersToDefaultRole();
    }

    private void initUsersAndCustomers() {
	List<Customer> addedCustomers = new ArrayList<>();
    	addedCustomers.add(new Customer("Bobby", "Bazini", "bobbizzi@starz.net", "814-842-4224", "Quebec", "QC"));
    	addedCustomers.add(new Customer("Lydia", "Sarcelle", "lidy1234@gmail.com", "(617)444-1234", "Montréal", "QC"));
    	addedCustomers.add(new Customer("Norman", "Weisle", "nweisle@canadagvt.ca", "(819)303-4004 #554", "Ottawa", "ON"));
    	
    	List<User> users = new ArrayList<>();
    	users.add(new User("user", "password"));
    	users.add(new User("lidy", "asdfghjk"));
    	users.add(new User("norm", "Gt7y^1aldP"));
    	
    	for(int i=0; i<3; i++){
    	    users.get(i).setCustomer(addedCustomers.get(i));
    	    userService.save(users.get(i));
    	}
	
    }

    private void initProducts() {
	prodService.save(new Product("Black Dragon", CategoryEnum.BLACK_TEA, BigDecimal.valueOf(16.0), 25));
	prodService.save(new Product("Dark Lord", CategoryEnum.BLACK_TEA, BigDecimal.valueOf(16.0), 25));
	prodService.save(new Product("White Dragon", CategoryEnum.WHITE_TEA, BigDecimal.valueOf(17.0), 22));
	prodService.save(new Product("Matcha", CategoryEnum.GREEN_TEA, BigDecimal.valueOf(20.0), 20));	
    }
    
    private void initOrderHistory() {
        List<User> users = userService.findAll();
        List<Product> products = (List<Product>) prodService.findAll();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }
    
    private void initCarts() {
        List<User> users = (List<User>) userService.findAll();
        List<Product> products = (List<Product>) prodService.findAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.save(user);
        });
    }
    
    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.findAll();
        List<User> users = (List<User>) userService.findAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.save(user);
                });
            }
        });
    }

    private void initRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.save(role);
    }

}
