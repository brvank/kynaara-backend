package com.retail.kynaara.repository;

import com.retail.kynaara.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
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

    //create operations
    public void addUser(User user){
        userRepository.save(user);
    }

    public void addMultipleUsers(ArrayList<User> userArrayList){
        userRepository.saveAll(userArrayList);
    }

    //read operations
    public List<User> getUsers(int start, int size, int userLevel){
        if(start < 0){
            start = 0;
        }
        if(size < 0){
            size = 0;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserLevel = criteriaBuilder.ge(userRoot.get("user_user_level"), userLevel);

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserLevel);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<User> getUserByUserId(int userId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserId = criteriaBuilder.equal(userRoot.get("user_id"), userId);

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserId);

        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    public List<User> getUsersByFullName(int start, int size, String q, int userLevel){
        if(start < 0){
            start = 0;
        }
        if(size < 0){
            size = 0;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserFullName = criteriaBuilder.like(userRoot.get("user_full_name"), "%" + q + "%");
        Predicate predicateUserLevel = criteriaBuilder.ge(userRoot.get("user_user_level"), userLevel);

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserFullName, predicateUserLevel);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<User> getUsersByUserName(int start, int size, String q, int userLevel){
        if(start < 0){
            start = 0;
        }
        if(size < 0){
            size = 0;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);

        Predicate predicateUserFullName = criteriaBuilder.like(userRoot.get("user_user_name"), "%" + q + "%");
        Predicate predicateUserLevel = criteriaBuilder.ge(userRoot.get("user_user_level"), userLevel);

        userCriteriaQuery.select(userRoot);

        userCriteriaQuery.where(predicateUserFullName, predicateUserLevel);

        return entityManager.createQuery(userCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<User> getUserByNameAndPassword(String username, String password){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userCriteriaQuery.from(User.class);
        Predicate predicateUserNameFilter = criteriaBuilder.like(userRoot.get("user_user_name"), username);
        Predicate predicatePasswordFilter = criteriaBuilder.like(userRoot.get("user_password"), password);

        userCriteriaQuery.where(predicateUserNameFilter, predicatePasswordFilter);

        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    //update operations
    @Transactional
    public void updateUser(User user){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);

        Root<User> userRoot = criteriaUpdate.from(User.class);

        criteriaUpdate.set("user_full_name", user.getUser_full_name());
        criteriaUpdate.set("user_email", user.getUser_email());
        criteriaUpdate.set("user_password", user.getUser_password());

        Predicate predicateUserId = criteriaBuilder.equal(userRoot.get("user_id"), user.getUser_id());

        criteriaUpdate.where(predicateUserId);

        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    //delete operations
    @Transactional
    public void deleteUser(int userId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);

        Root<User> userRoot = criteriaDelete.from(User.class);

        Predicate predicateUserId = criteriaBuilder.equal(userRoot.get("user_id"), userId);

        criteriaDelete.where(predicateUserId);

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
