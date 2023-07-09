package com.retail.kynaara.repository;

import com.retail.kynaara.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    public List<User> getUserByNameAndPassword(String username, String password){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);
        Predicate predicateUserNameFilter = criteriaBuilder.like(userRoot.get("user_user_name"), username);
        Predicate predicatePasswordFilter = criteriaBuilder.like(userRoot.get("user_password"), password);

        userCriteriaQuery.where(predicateUserNameFilter, predicatePasswordFilter);

        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    //four basic operations
    // CRUD -> create, read, update, delete
    // create -> add user
    // read -> get user
    // update -> edit user
    // delete -> remove user


    //create operations
    public void addUser(User user){
        userRepository.save(user);
    }


    public void addMultipleUsers(ArrayList<User> userArrayList){
        userRepository.saveAll(userArrayList);
    }

    //read operations
    public List<User> getUsers(int start, int size){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        userCriteriaQuery.select(userRoot);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<User> getUsersByFullName(int start, int size, String q){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserFullName = criteriaBuilder.like(userRoot.get("user_full_name"), "%" + q + "%");

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserFullName);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<User> getUsersByUserName(int start, int size, String q){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserFullName = criteriaBuilder.like(userRoot.get("user_user_name"), "%" + q + "%");

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserFullName);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }
}
