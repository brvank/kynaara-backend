package com.retail.kynaara.repository;

import com.retail.kynaara.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;
    
    //create operations
    public void addProduct(Product product){
        productRepository.save(product);
    }
    
    public void addMultipleProducts(ArrayList<Product> productArrayList){
        productRepository.saveAll(productArrayList);
    }
    
    //read operations
    public List<Product> getProducts(int start, int size){
        if(start < 0){
            start = 0;
        }
        if(size < 0){
            size = 0;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        productCriteriaQuery.select(productRoot);

        return entityManager.createQuery(productCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<Product> getProductByProductId(int productId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        Predicate predicateProductId = criteriaBuilder.equal(productRoot.get("product_id"), productId);

        productCriteriaQuery.select(productRoot);

        productCriteriaQuery.where(predicateProductId);

        return entityManager.createQuery(productCriteriaQuery).getResultList();
    }

    //update operations
    @Transactional
    public void updateProduct(Product product){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);

        Root<Product> productRoot = criteriaUpdate.from(Product.class);

        criteriaUpdate.set("product_link", product.getProduct_link());
        criteriaUpdate.set("product_image_link", product.getProduct_image_link());

        Predicate predicateProductId = criteriaBuilder.equal(productRoot.get("product_id"), product.getProduct_id());

        criteriaUpdate.where(predicateProductId);

        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    //delete operations
    @Transactional
    public void deleteProduct(int productId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);

        Root<Product> productRoot = criteriaDelete.from(Product.class);

        Predicate predicateProductId = criteriaBuilder.equal(productRoot.get("product_id"), productId);

        criteriaDelete.where(predicateProductId);

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
