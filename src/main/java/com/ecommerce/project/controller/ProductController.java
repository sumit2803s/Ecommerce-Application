package com.ecommerce.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.project.config.AppContants;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long categoryId){
        ProductDTO savedproductDTO= productService.addProduct(categoryId,productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllResponse(@RequestParam(name = "pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                          @RequestParam(name = "pageSize",defaultValue = AppContants.PAGE_SIZE,required = false) Integer pageSize,
                                                          @RequestParam(name = "sortBy",defaultValue = AppContants.SORT_CATEGORIES_BY,required = false)String sortBy,
                                                          @RequestParam(name = "sortOrder",defaultValue = AppContants.SORT_DIR,required = false)String sortOrder){
        ProductResponse productResponse=productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId,
                                                                 @RequestParam(name = "pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize",defaultValue = AppContants.PAGE_SIZE,required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy",defaultValue = AppContants.SORT_CATEGORIES_BY,required = false)String sortBy,
                                                                 @RequestParam(name = "sortOrder",defaultValue = AppContants.SORT_DIR,required = false)String sortOrder){
        ProductResponse productResponse=productService.searchByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword,
                                                                @RequestParam(name = "pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                @RequestParam(name = "pageSize",defaultValue = AppContants.PAGE_SIZE,required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy",defaultValue = AppContants.SORT_CATEGORIES_BY,required = false)String sortBy,
                                                                @RequestParam(name = "sortOrder",defaultValue = AppContants.SORT_DIR,required = false)String sortOrder){
    ProductResponse productResponse=productService.searchProductByKeyword(keyword,pageNumber,pageSize,sortBy,sortOrder);
    return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }
    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,@PathVariable Long productId){
    ProductDTO updatedProductDTO=productService.updateProduct(productId,productDTO);
    return new ResponseEntity<>(updatedProductDTO,HttpStatus.OK);
    }
    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){

        ProductDTO deletedProduct = productService.deletedProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId, @RequestParam("image")MultipartFile image) throws IOException {
        ProductDTO updatedProduct=productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }
}
