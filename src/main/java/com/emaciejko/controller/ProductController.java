package com.emaciejko.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emaciejko.domain.Product;
import com.emaciejko.service.ProductService;

@Controller
public class ProductController {
    
    private Path path;
    
    @Value("${emaciejko.paths.uploadedFiles}")
    private String uploadFilePath;
    
    //private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    private ProductService prodService;
    
    @Autowired
    public ProductController(ProductService prodService){
	this.prodService = prodService;
    }

    private String getProductImageFilename(Long productId){
	path = Paths.get(uploadFilePath + "\\" + productId + ".png");
	return path.toString();
    }
    
    @RequestMapping(path="/products", method=RequestMethod.GET)
    public String viewProducts(Model model){
	Iterable<Product> prodList = prodService.findAll();
	model.addAttribute("prodList", prodList);
	return "/view/product/list";
    }
    
    @RequestMapping(path="/product/{id}", method=RequestMethod.GET)
    public String details(@PathVariable int id, Model model){
	Product prod = prodService.findOne(id);
	model.addAttribute("prod", prod);	
	return "/view/product/details";
    }        
    
    // This is to serve the uploaded image for display in web page
    @RequestMapping(path="/product/{id}/image",method=RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) throws IOException{
		
	File serverFile = new File(getProductImageFilename(id));
	return Files.readAllBytes(serverFile.toPath());
    }
    
    @RequestMapping(path="/product/new", method=RequestMethod.GET)
    public String newProduct(Model model){
	Product prod = new Product("New");
	model.addAttribute("prod", prod);
	model.addAttribute("categoryArray", Product.CategoryEnum.values());
	return "view/product/form";
    }
    
    @RequestMapping(path="/product/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable int id, Model model){
	Product prod = prodService.findOne(id);
	model.addAttribute("prod", prod);
	model.addAttribute("categoryArray", Product.CategoryEnum.values());
	return "view/product/form";
    }
    
    @RequestMapping(path="/products", method=RequestMethod.POST)    
    public String addProduct(@Valid @ModelAttribute("prod") Product product,	    
	    BindingResult bindingRes,
	    Model model,
	    RedirectAttributes redirectAttr){
	
	if(bindingRes.hasErrors()){
	    model.addAttribute("prod", product);
	    model.addAttribute("categoryArray", Product.CategoryEnum.values());
	    return "view/product/form";
	}
	
	prodService.save(product);
	
	// Though the image file is bound automatically to the product,
	// we must save it locally on machine because it is transient and will not be put in database.
	MultipartFile image = product.getImage();
	
	if(image != null && !image.isEmpty()){
	    path = Paths.get(uploadFilePath);
	    try{
		// Test if path exists; else, create it
		File file =new File(path.toString()); 
		if(!file.exists()){
		    file.mkdirs();
		}
		image.transferTo(new File(getProductImageFilename(product.getId())));
	    }
	    catch(Exception e){
		e.printStackTrace();
		redirectAttr.addFlashAttribute("error",  "Image " +  image.getOriginalFilename()+ " failed to be saved.");
		return "redirect:/admin/inventory";
	    }
	}	
	redirectAttr.addFlashAttribute("success", "Product  (" + product.getName() + ") was successfully saved.");
	return "redirect:/admin/inventory";	
    }
    
    @RequestMapping(path="/product/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttr){
	prodService.delete(id);
	return id.toString();
    }
}
