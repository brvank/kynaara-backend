package com.retail.kynaara.repository;

import com.retail.kynaara.model.Product;
import com.retail.kynaara.response_model.CountResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
    public List<Product> getProducts(int start, int size, int channelId, Integer assigneeId, String q){
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

        Predicate predicate = criteriaBuilder.conjunction();

        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productRoot.get("product_channel_id"), channelId));

        if(assigneeId != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productRoot.get("product_assignee_id"), assigneeId));
        }

        if(q != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(productRoot.get("product_link"), "%" + q + "%"));
        }

        productCriteriaQuery.where(predicate);

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

    @Transactional
    public void assignProduct(Integer assigneeId, int productId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);

        Root<Product> productRoot = criteriaUpdate.from(Product.class);

        criteriaUpdate.set("product_assignee_id", assigneeId);

        if(assigneeId == null){
            criteriaUpdate.set("product_date_assigned", null);
        }else{
            criteriaUpdate.set("product_date_assigned", LocalDateTime.now());
        }

        Predicate predicateProductId = criteriaBuilder.equal(productRoot.get("product_id"), productId);

        criteriaUpdate.where(predicateProductId);

        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Transactional
    public void resetProductsAssignee(Integer assigneeId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);

        Root<Product> productRoot = criteriaUpdate.from(Product.class);

        criteriaUpdate.set("product_assignee_id", null);
        criteriaUpdate.set("product_date_assigned", null);

        Predicate predicateProductId = criteriaBuilder.equal(productRoot.get("product_assignee_id"), assigneeId);

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

    @Transactional
    public void deleteProducts(int channelId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);

        Root<Product> productRoot = criteriaDelete.from(Product.class);

        Predicate predicateChannelId = criteriaBuilder.equal(productRoot.get("product_channel_id"), channelId);

        criteriaDelete.where(predicateChannelId);

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    //count operations
    public Long getCountProducts(int channelId, Integer assigneeId, String q){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> productCriteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        productCriteriaQuery.select(criteriaBuilder.count(productRoot));

        Predicate predicate = criteriaBuilder.conjunction();

        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productRoot.get("product_channel_id"), channelId));

        if(assigneeId != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productRoot.get("product_assignee_id"), assigneeId));
        }

        if(q != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(productRoot.get("product_link"), "%" + q + "%"));
        }

        productCriteriaQuery.where(predicate);

        return entityManager.createQuery(productCriteriaQuery).getSingleResult();
    }
}
