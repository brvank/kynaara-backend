package com.retail.kynaara.controller;

import com.retail.kynaara.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/product")
@ResponseBody
public class ProductController extends ParentController{
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody Map<String, Object> productMap, HttpServletRequest header){
        return productService.addProduct(productMap, headerToUser(header));
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getProducts(@RequestParam int start, @RequestParam int size, @RequestParam int channelId, HttpServletRequest header){
        return productService.getProducts(start, size, channelId, headerToUser(header));
    }

    @GetMapping("/get/byAssigneeId")
    public ResponseEntity<Object> getProductsByAssigneeId(@RequestParam int start, @RequestParam int size, @RequestParam int channelId, @RequestParam int assigneeId, HttpServletRequest header){
        return productService.getProductsByAssigneeId(start, size, channelId, assigneeId, headerToUser(header));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getProductByProductId(@PathVariable(value = "id") int productId, HttpServletRequest header){
        return productService.getProductByProductId(productId, headerToUser(header));
    }

    @GetMapping("/get/byLink")
    public ResponseEntity<Object> getProductsByLink(@RequestParam int start, @RequestParam int size, @RequestParam int channelId, @RequestParam String q, HttpServletRequest header){
        return productService.getProductsByLink(start, size, channelId, q, headerToUser(header));
    }

    @GetMapping("/get/byLinkByAssigneeId")
    public ResponseEntity<Object> getProductsByLinkByAssigneeId(@RequestParam int start, @RequestParam int size, @RequestParam int channelId, @RequestParam int assigneeId, @RequestParam String q, HttpServletRequest header){
        return productService.getProductsByLinkByAssigneeId(start, size, channelId, assigneeId, q, headerToUser(header));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Map<String, Object> productMap, HttpServletRequest header){
        return productService.updateProduct(productMap, headerToUser(header));
    }

    @PutMapping("/assign")
    public ResponseEntity<Object> assignProduct(@RequestBody Map<String, Object> productMap, HttpServletRequest header){
        return productService.assignProduct(productMap, headerToUser(header));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") int productId, HttpServletRequest header){
        return productService.deleteProduct(productId, headerToUser(header));
    }
}
