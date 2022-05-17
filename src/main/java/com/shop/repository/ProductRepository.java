package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where category_id=?1")
    List<Product> findAllByCategoryId(Long id, Pageable pageable);

    @Query("select count(p) from Product p where category_id=?1")
    Integer countAllByCategoryId(Long id);
}
