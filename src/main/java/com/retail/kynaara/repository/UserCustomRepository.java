package com.retail.kynaara.repository;

import com.retail.kynaara.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCustomRepository {

    @Autowired
    EntityManager entityManager;

    public List<User> getUsers(String q){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);
        Predicate predicateUserNameFilter = criteriaBuilder.like(userRoot.get("name"), "%s");

        userCriteriaQuery.where(predicateUserNameFilter);

        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    public List<User> getUserByNameAndPassword(String username, String password){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);
        Predicate predicateUserNameFilter = criteriaBuilder.like(userRoot.get("userName"), username);
        Predicate predicatePasswordFilter = criteriaBuilder.like(userRoot.get("password"), password);

        userCriteriaQuery.where(predicateUserNameFilter, predicatePasswordFilter);

        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }
}
