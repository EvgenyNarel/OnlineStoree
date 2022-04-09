package com.narel.online_store.controller;

import com.narel.online_store.dao.User;
import com.narel.online_store.model.CartItem;
import com.narel.online_store.model.Product;
import com.narel.online_store.repository.ProductRepository;
import com.narel.online_store.service.ProductServiceImpl;
import com.narel.online_store.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ShoppingCartServices cartServices;

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService, ProductRepository productRepository) {
        this.productService = productService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(String name) {
        return "main";
    }

    @GetMapping("/product")
    public String findAll(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Product> product = productService.findAll();
        if (filter != null && !filter.isEmpty()) {
            product = productService.findByName(filter);
        } else {
            product = productService.findAll();
        }
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);

        return "product-list";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product) {
        return "product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(@RequestParam("file") MultipartFile file, Product product) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
            product.setFilename(file.getOriginalFilename());
        }
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(@RequestParam("file") MultipartFile file, Product product) throws IOException {

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
        product.setFilename(file.getOriginalFilename());

        productService.saveProduct(product);
        return "redirect:/product";
    }


    @GetMapping("/laptop/{id}")
    public String laptopForm(@PathVariable("id") Long id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CartItem> cartItems = cartServices.listCartItems((User) principal);

        model.addAttribute("cartItems", cartItems);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "laptop";
    }


}



