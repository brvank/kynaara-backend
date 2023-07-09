package com.retail.kynaara.repository;

import com.retail.kynaara.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
