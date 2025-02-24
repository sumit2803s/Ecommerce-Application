package com.ecommerce.project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pagedetails);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pagedetails);
}
